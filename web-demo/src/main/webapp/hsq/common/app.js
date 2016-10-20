/**
 * Created by YYJ on 2016/2/8.
 */
"use strict";
define(function (require, exports, module) {
    var appload = require('./app_load');
    var apptransition = require('./app_transition');
    var apptemplate = require('apptemplate');

    var _opt = {
            initHash: 'main',
            container: '.container',
            isdummy: false,
            url_js: './js/'
        }, t = {
            extend: function (o, n) {
                for (var e in n) {
                    o[e] = n[e];
                }
            }
        },
        $container,
        _pageStack = [],
        _startIndex = 0,
        _index = 0,
        appdata = {},
        animate = function () {
        },
        getResourcePath = function (hash) {
            return hash.indexOf('html') !== -1 ? hash.replace(/^#?([^#?]+)(\?)?.*/, '$1.html') : _opt.url_js + hash.replace(/^#?([^#?]+)(\?)?.*/, '$1');
        },
        getPageId = function (hash) {
            return hash.replace(/^#?([^#?]+)(\?)?.*/, '$1').replace(/\//g, '_');
        },
        getParam = function (hash) {
            var paramstr = hash.indexOf("?") !== -1 ? hash.substring(hash.indexOf("?") + 1) : "",
                paramarray = paramstr.split('&'), result = {};
            for (var i in paramarray) {
                var key = paramarray[i].split('=')[0], value = paramarray[i].split('=')[1];
                result[key] = value;
            }
            return result;
        },
        pageExist = function (page_id) {
            return !!document.querySelector("section#" + page_id);
        },
        getPageConfig = function (page_id) {
            for (var i = 0; i < _pageStack.length; i++) {
                if (page_id == _pageStack[i].page_id)return _pageStack[i];
            }
            return null;
        },
        getPage = function (page_id) {
            var config = getPageConfig(page_id);
            return config && config.page || null;
        },
        loadContent = function (hash, callback, param) {
            console.log('app:load(' + hash + ')');
            if (!hash)return;
            var url = getResourcePath(hash);
            seajs.use(url, function (page) {
                if (!page) return function () {
                    console.log(url + '.js not found');
                }();
                var html, page = typeof(page) === 'string' && (html = page) && {html: html} || page;
                var page_id = getPageId(hash), config = {
                    page_id: page_id,
                    class_name: page_id,
                    page: page
                };
                _pageStack.push(config);
                callback(config);
            });
        },
        _addHistory = function (hash, data, isreplace) {
            _index++;
            isreplace && history.replaceState && (history.replaceState(data && {_index: _index, _data: data} || {_index: _index}, "", location.href.substring(0, location.href.indexOf('#')) + hash));
            !isreplace && history.pushState && (history.pushState(data && {_index: _index, _data: data} || {_index: _index}, "", location.href.substring(0, location.href.indexOf('#')) + hash));
        },
        _createSection = function (config, isback) {
            var page = config.page, page_id = config.page_id;
            var $target = 'section#' + page_id;
            !isback && !page.forwardcacha && $($target).remove();
            if (!pageExist(page_id)) {
                var el = document.createElement("section");
                el.id = page_id;
                el.innerHTML = page && page.tpl && apptemplate.compile(page.tpl, page.pagedata) || page.html && page.html || "";
                $container.appendChild(el);
            } else {
            }
            page && page.render && page.render();
            var $current = 'section.active';
            var $cur = $($current);
            apptransition.run($current, $target, isback);
        },
        _getData = function (config, param, data, isback, nohistory) {//数据相关
            var page = config.page, page_id = config.page_id;
            console.log('app:go(' + _index + '_' + page_id + ')');

            // 数据优先级:1.preload>2.data>3.history.state._data
            if (page.preload && (!pageExist(page_id) || !isback)) {//前进或无section
                page.preload(param, function (data) {
                    data && (data.param = param);
                    //page.setData && page.setData(data);
                    page.pagedata = data;
                    _createSection(config, isback);
                });
            } else {
                if (!pageExist(page_id) || !isback) {
                    !data && history.state && history.state._data && (data = history.state._data);
                    data && (data.param = param);
                    //page.setData && page.setData(data);
                    page.pagedata = data;
                } else {
                }
                _createSection(config, isback);
            }
        },
        go = function (hash, data, isback, nohistory, isreplace) {//模块加载相关
            if (!hash)return;
            hash.indexOf('#') < 0 && (hash = '#' + hash);
            !nohistory && _addHistory(hash, data, isreplace);
            // 1.判别是否加载过模块
            var page_id = getPageId(hash), config = getPageConfig(page_id), param = getParam(hash);
            // 2.未加载过页面模块js/html时，加载模块，并初始化
            !config && loadContent(hash, function (config) {
                config.page.init && config.page.init(data);
                config.page.bind && config.page.bind(data);
                _getData(config, param, data, isback, nohistory);
            });
            // 3.已加载过页面模块js/html时，做跳转处理
            config && _getData(config, param, data, isback, nohistory);
        },
        back2home = function () {
            history.go(_startIndex - _index);
        },
        back = function (no) {
            history.go(no || -1);
        },
        canGoBack = function () {
            return !!(history.state && history.state._index);
        },
        isdummy = function () {
            return _opt.isdummy;
        },
        launch = function (options) {
            console.log('app:launch');
            t.extend(_opt, options);
            var hash = location.hash;
            hash.indexOf('dummy') !== -1 && (_opt.isdummy = true);

            $container = document.querySelector(_opt.container);

            window.onhashchange = function (e) {
                console.log('app:hashchange:' + location.hash);
                //获取hash,search
                var hash = location.hash;
                //var page_id = getPageId(location.hash);
                //var param = getParam(location.search);
                var state = history.state || {};
                var isback = _index >= state._index;
                isback ? _index-- : _index++;
                history.replaceState && history.replaceState(history.state && history.state._data && {
                        _index: _index,
                        _data: history.state._data
                    } || {
                        _index: _index
                    },
                    "", location.href);
                go(hash, "", isback, true);
            };
            window.onpopstate = function (e) {
                //console.log('app:onpopstate');
            };
            // 初始化
            history.state && history.state._index && (_index = history.state._index);
            //page_id == '' && (location.hash = _opt.init) || go(page_id, "", true);
            !hash && go(_opt.initHash, "", false, false);
            if (hash) {
                history.replaceState && history.replaceState(history.state && history.state._data && {
                        _index: _index,
                        _data: history.state._data
                    } || {
                        _index: _index
                    },
                    "", location.href);
                go(hash, "", false, true);
            }
            //hash && go(hash, "", false, false);
            //_startIndex = _index;
        }, request = function (param) {
            if (!param)return;
            isdummy() && (param.dummy = param.url.dummy);
            param.url = param.url.url;
            appload.request(param);
        };

    return {
        go: go,
        back2home: back2home,
        back: back,
        run: launch,
        //data: appdata,
        page: getPage,
        canGoBack: canGoBack,
        isdummy: isdummy,
        request: request
    };
});