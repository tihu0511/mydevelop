package org.hsq.wjg.demo.generator;

import com.hsq.component.file.FileUtil;
import com.hsq.component.file.excel.ExcelUtil;
import com.hsq.component.lang.StringUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.hsq.wjg.demo.generator.constants.GeneratorConstants;
import org.hsq.wjg.demo.generator.pojo.ws.DtoEntity;
import org.hsq.wjg.demo.generator.pojo.ws.FieldEntity;
import org.hsq.wjg.demo.generator.pojo.ws.WsEntity;
import org.hsq.wjg.demo.generator.pojo.ws.WsMethod;
import org.hsq.wjg.demo.generator.util.NameUtil;
import org.hsq.wjg.demo.generator.util.PrintUtil;
import org.hsq.wjg.demo.generator.util.ResourcesTemplateUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wujigang on 2016/11/7.
 */
public class WsGenerator {
    private static final String WS_TPL_PATH = "/tpl/ws/ws.vm";
    private static final String WS_IMPL_TPL_PATH = "/tpl/ws/wsImpl.vm";
    private static final String DTO_TPL_PATH = "/tpl/ws/dto.vm";
    private static final String BASE_IN_DTO_TPL_PATH = "/tpl/ws/baseInDto.vm";
    private static final String BASE_OUT_DTO_TPL_PATH = "/tpl/ws/baseOutDto.vm";

    private String mainPackage; //主包文件绝对路径
    private String commonProjectPath; //common模块路径
    private String serviceProjectPath; //项目模块路径
    private String mainPackagePath;

    private boolean hasNoBaseDto; //是否已经生成过baseDto
    private List<String> generatedDtoes = new ArrayList<String>(); //已经生成过的dto

    private static Map<String, String> javaTypeMap = new HashMap<String, String>();

    static {
        javaTypeMap.put("Integer", "java.lang.Integer");
        javaTypeMap.put("int", "java.lang.Integer");
        javaTypeMap.put("Double", "java.lang.Double");
        javaTypeMap.put("double", "java.lang.Double");
        javaTypeMap.put("BigDecimal", "java.math.BigDecimal");
        javaTypeMap.put("Date", "java.util.Date");
        javaTypeMap.put("Boolean", "java.lang.Boolean");
    }

    public WsGenerator(String mainPackage, String commonProjectPath, String serviceProjectPath) {
        this.mainPackage = mainPackage;
        this.commonProjectPath = commonProjectPath;
        this.serviceProjectPath = serviceProjectPath;
        this.mainPackagePath = commonProjectPath + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator
                + mainPackage.replace(".", File.separator) + File.separator;
    }

    public WsGenerator(String mainPackage, String projectPath, String commonProjectName, String serviceProjectName) {
        this.mainPackage = mainPackage;
        this.commonProjectPath = projectPath + File.separator + commonProjectName;
        this.serviceProjectPath = projectPath + File.separator + serviceProjectName;;
        this.mainPackagePath = commonProjectPath + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator
                + mainPackage.replace(".", File.separator) + File.separator;
    }

    /**
     * 生成代码
     * @param file
     * @throws IOException
     */
    public void generate(String file) throws IOException {
        List<WsEntity> wsEntities = parseWsEntity(file);

        for (WsEntity wsEntity : wsEntities) {
            generateWs(wsEntity);
            generateWsImpl(wsEntity);
            generateDto(wsEntity);
        }
    }

    private void generateDto(WsEntity wsEntity) throws IOException {
        if (!hasNoBaseDto) {
            generateBaseDto();
            hasNoBaseDto = true;
        }

        for (WsMethod method : wsEntity.getWsMethods()) {
            String subPackage = wsEntity.getName().substring(0, 1).toLowerCase() + wsEntity.getName().substring(1);
            String dtoDir = mainPackagePath + "dto" + File.separator + subPackage + File.separator;
            FileUtil.mkdirs(dtoDir);

            DtoEntity inDto = method.getInDto();
            String inDtoFile = dtoDir + inDto.getName() + ".java";
            //如果已经生成过，则不再生成
            if (generatedDtoes.contains(inDtoFile)) {
                continue;
            }

            String inDtoContent = getDtoContent(inDto, subPackage, 1);
            FileUtil.writeFile(inDtoFile, inDtoContent);
            generatedDtoes.add(inDtoFile);

            DtoEntity outDto = method.getOutDto();
            String outDtoContent = getDtoContent(outDto, subPackage, 2);
            String outDtoFile = dtoDir + outDto.getName() + ".java";
            FileUtil.writeFile(outDtoFile, outDtoContent);
            generatedDtoes.add(outDtoFile);
        }
    }

    private void generateBaseDto() throws IOException {
        String baseDtoDir = mainPackagePath + "dto" + File.separator + "base" + File.separator;
        FileUtil.mkdirs(baseDtoDir);

        //BaseInDto
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mainPackage", mainPackage);
        FileUtil.writeFile(baseDtoDir + "BaseInDto.java",
                ResourcesTemplateUtil.getTemplateContent(params, BASE_IN_DTO_TPL_PATH));
        //BaseOutDto
        FileUtil.writeFile(baseDtoDir + "BaseOutDto.java",
                ResourcesTemplateUtil.getTemplateContent(params, BASE_OUT_DTO_TPL_PATH));

    }

    private String getDtoContent(DtoEntity dto, String subPackage, int type) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("package", mainPackage + ".dto." + subPackage);
        params.put("generatedTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //imports
        List<String> imports = new ArrayList<String>();
        for (FieldEntity field : dto.getFields()) {
            String typeImport = javaTypeMap.get(field.getType());
            if (StringUtil.hasLength(typeImport)) {
                imports.add(typeImport);
            }
        }
        //BaseDto
        if (type == 1) {
            params.put("baseDtoName", "BaseInDto");
            imports.add(mainPackage + ".dto.base.BaseInDto");
        } else {
            params.put("baseDtoName", "BaseOutDto");
            imports.add(mainPackage + ".dto.base.BaseOutDto");
        }
        params.put("imports", imports);
        Collections.sort(imports);
        params.put("dto", dto);
        return ResourcesTemplateUtil.getTemplateContent(params, DTO_TPL_PATH);
    }

    private void generateWs(WsEntity wsEntity) throws IOException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("wsEntity", wsEntity);
        params.put("generatedTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //imports
        List<String> imports = new ArrayList<String>();

        String subPackage = wsEntity.getName().substring(0, 1).toLowerCase() + wsEntity.getName().substring(1);
        for (WsMethod method : wsEntity.getWsMethods()) {
            //InDto
            DtoEntity inDto = method.getInDto();
            String inDtoImport = mainPackage + ".dto." + subPackage + "." + inDto.getName();
            if (!imports.contains(inDtoImport)) {
                imports.add(inDtoImport);
            }
            //OutDto
            DtoEntity outDto = method.getOutDto();
            String outDtoImport = mainPackage + ".dto." + subPackage + "." + outDto.getName();
            if (!imports.contains(outDtoImport)) {
                imports.add(outDtoImport);
            }
            Collections.sort(imports);
        }
        params.put("imports", imports);

        String filePackagePath = mainPackagePath + "ws" + File.separator;
        FileUtil.mkdirs(filePackagePath);
        String filePath = filePackagePath + "I" + wsEntity.getName() + "Ws.java";
        FileUtil.writeFile(filePath, ResourcesTemplateUtil.getTemplateContent(params, WS_TPL_PATH), GeneratorConstants.DEFAULT_CHARSET);
    }

    private void generateWsImpl(WsEntity wsEntity) throws IOException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("wsEntity", wsEntity);
        params.put("generatedTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //imports
        List<String> imports = new ArrayList<String>();
        imports.add(mainPackage + ".ws.I" + wsEntity.getName() + "Ws");

        String subPackage = wsEntity.getName().substring(0, 1).toLowerCase() + wsEntity.getName().substring(1);
        for (WsMethod method : wsEntity.getWsMethods()) {
            //InDto
            DtoEntity inDto = method.getInDto();
            String inDtoImport = mainPackage + ".dto." + subPackage + "." + inDto.getName();
            if (!imports.contains(inDtoImport)) {
                imports.add(inDtoImport);
            }
            //OutDto
            DtoEntity outDto = method.getOutDto();
            String outDtoImport = mainPackage + ".dto." + subPackage + "." + outDto.getName();
            if (!imports.contains(outDtoImport)) {
                imports.add(outDtoImport);
            }
        }
        Collections.sort(imports);
        params.put("imports", imports);

        String filePackagePath = mainPackagePath + "ws" + File.separator;
        FileUtil.mkdirs(filePackagePath);
        String filePath = filePackagePath + wsEntity.getName() + "WsImpl.java";
        FileUtil.writeFile(filePath, ResourcesTemplateUtil.getTemplateContent(params, WS_IMPL_TPL_PATH));
    }

    /**
     * 解析文件为WsEntity对象
     * @param file
     * @return
     * @throws IOException
     */
    private List<WsEntity> parseWsEntity(String file) throws IOException {
        List<Sheet> sheets = ExcelUtil.readExcelSheets(file);
        List<WsEntity> wsEntities = new ArrayList<WsEntity>();

        for (Sheet sheet : sheets) {
            String sheetName = sheet.getSheetName();
            int index = sheetName.indexOf("(");
            if (index < 0) {
                PrintUtil.println("%s不包含接口名称，跳过", sheetName);
                continue;
            }
            String wsName = sheetName.substring(index + 1, sheetName.indexOf(")"));

            //接口对象
            WsEntity wsEntity = new WsEntity();
            wsEntity.setName(wsName);
            wsEntity.setDesc(sheetName.substring(0, index));
            wsEntity.setMainPackage(mainPackage);
            final List<WsMethod> wsMethods = new ArrayList<WsMethod>();
            wsEntity.setWsMethods(wsMethods);

            //读取每一行，生成接口方法
            if (null == sheet || sheet.getLastRowNum() < 0) {
                PrintUtil.println("表格页%s中没有数据", sheet.getSheetName());
                continue;
            }
            //行
            WsMethod wsMethod = null;
            DtoEntity inDto = null;
            DtoEntity outDto = null;
            int splitRowIndex = -99;
            int rowDtoType = 0;
            for (int rowNum = sheet.getFirstRowNum(); rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row row = sheet.getRow(rowNum);
                if (row == null || row.getFirstCellNum() < 0 || row.getLastCellNum() < 0) {
                    continue;
                }

                //处理行
                if (rowNum == splitRowIndex) {
                    continue;
                }
                String str = row.getCell(row.getFirstCellNum()).getStringCellValue();
                if (str.indexOf("接口") >= 0) {
                    //新的接口方法
                    if (null != wsMethod) {
                        wsMethods.add(wsMethod);
                    }
                    wsMethod = new WsMethod();
                    wsMethod.setName(str.substring(str.indexOf("(") + 1, str.indexOf(")")));
                    wsMethod.setDesc(str.substring(0, str.indexOf("(")));
                } else if (str.indexOf("入参") >= 0) {
                    inDto = new DtoEntity();
                    wsMethod.setInDto(inDto); //设置接口方法的入参
                    inDto.setName(str.substring(str.indexOf("(") + 1, str.indexOf(")")));
                    inDto.setDesc(wsMethod.getDesc() + str.substring(0, str.indexOf("(")));
                    splitRowIndex = rowNum + 1; //设置入参标题分隔行
                    rowDtoType = 1; //设置为inDto
                } else if (str.indexOf("出参") >= 0) {
                    outDto = new DtoEntity();
                    wsMethod.setOutDto(outDto); //设置接口方法的出参
                    outDto.setName(str.substring(str.indexOf("(") + 1, str.indexOf(")")));
                    outDto.setDesc(wsMethod.getDesc() + str.substring(0, str.indexOf("(")));
                    splitRowIndex = rowNum + 1; //设置出参标题分隔行
                    rowDtoType = 2; //设置为outDto
                } else if (StringUtil.hasLength(str) && rowDtoType == 1) {
                    int firstCell = row.getFirstCellNum();
                    FieldEntity field = new FieldEntity();
                    field.setName(row.getCell(firstCell + 1).getStringCellValue());
                    field.setType(row.getCell(firstCell + 2).getStringCellValue());
                    field.setIsMust("Y".equalsIgnoreCase(row.getCell(firstCell + 3).getStringCellValue()));
                    field.setDesc(row.getCell(firstCell).getStringCellValue() + "," + row.getCell(firstCell + 4).getStringCellValue());
                    inDto.addFiled(field);
                } else if (StringUtil.hasLength(str) && rowDtoType == 2) {
                    int firstCell = row.getFirstCellNum();
                    FieldEntity field = new FieldEntity();
                    field.setName(row.getCell(firstCell + 1).getStringCellValue());
                    field.setType(row.getCell(firstCell + 2).getStringCellValue());
                    field.setDesc(row.getCell(firstCell).getStringCellValue() + "," + row.getCell(firstCell + 3).getStringCellValue());
                    outDto.addFiled(field);
                } else {
                    PrintUtil.println("%s页第%s行为空行或不可识别的列", sheetName, row.getRowNum());
                }


            }
            if (null != wsMethod) {
                wsMethods.add(wsMethod);
            }

            wsEntities.add(wsEntity);
        }
        return wsEntities;
    }
}
