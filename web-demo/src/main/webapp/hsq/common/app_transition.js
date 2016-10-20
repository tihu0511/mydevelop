/**
 * Created by YYJ on 2016/2/8.
 */
"use strict";
define(function (require, exports, module) {
    require('zepto');//TODO

    var $current, $target, isBack, transitionName,
        animationClass = {
            //[[currentOut,targetIn],[currentOut,targetIn]]
            slide: [['slideLeftOut', 'slideLeftIn'], ['slideRightOut', 'slideRightIn']],
            cover: [['', 'slideLeftIn'], ['slideRightOut', '']],
            slideUp: [['', 'slideUpIn'], ['slideDownOut', '']],
            slideDown: [['', 'slideDownIn'], ['slideUpOut', '']],
            popup: [['', 'scaleIn'], ['scaleOut', '']],
            none: [['', ''], ['', '']]
        };
    var _doTransition = function () {
        //触发 beforepagehide 事件
        $current.trigger('beforepagehide', [isBack]);
        //触发 beforepageshow 事件
        $target.trigger('beforepageshow', [isBack]);
        var c_class = transitionName[0] || 'empty', t_class = transitionName[1] || 'empty';
        $current.addClass('anim ' + c_class);
        $target.bind('webkitAnimationEnd.jingle', _finishTransition).addClass('anim animating ' + t_class);
        //$current.addClass(c_class);
        //$target.addClass('anim animating ' + t_class);
    };
    var _finishTransition = function () {
        $current.off('webkitAnimationEnd.jingle');
        $target.off('webkitAnimationEnd.jingle');
        //reset class
        //$current.attr('class','');
        //$target.addClass('active');
        var c_class = transitionName[0] || 'empty', t_class = transitionName[1] || 'empty';
        $current.removeClass('active anim ' + c_class);
        $target.removeClass('anim animating ' + t_class).addClass('active');
        //add custom events
        !$target.data('init') && $target.trigger('pageinit').data('init', true);
        !$current.data('init') && $current.trigger('pageinit').data('init', true);
        //触发pagehide事件
        $current.trigger('pagehide', [isBack]);
        //触发pageshow事件
        $target.trigger('pageshow', [isBack]);

        $current.find('article.active').trigger('articlehide');
        $target.find('article.active').trigger('articleshow');
        $current = $target = null;//释放
    };
    exports.run = function (current, target, back) {
        //关闭键盘
        $(':focus').trigger('blur');
        isBack = back;
        $current = $(current);
        $target = $(target);
        var type = isBack ? $current.attr('data-transition') : $target.attr('data-transition');
        type = type || 'slide';
        //后退时取相反的动画效果组
        transitionName = isBack ? animationClass[type][1] : animationClass[type][0];
        _doTransition();
    };
});