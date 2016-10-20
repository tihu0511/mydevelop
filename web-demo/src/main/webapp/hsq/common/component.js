define(function (require, exports, module) {
    exports.captchaCounter = function (opt) {
        var _maxTime = opt.maxTime || 60,
            _curTime = opt.maxTime || 60,
            _timer = null,
            _isBlock = false,
            _target = opt.target,
            _$t;
        var _start = function () {
            if (_isBlock)return;
            _isBlock = true;
            _$t = document.querySelector(_target);
            _$t.innerHTML = _curTime-- + '秒';
            _timer = setInterval(function () {
                if (_curTime == 0) {
                    _reset('重新获取');
                } else {
                    _$t.innerHTML = _curTime-- + '秒';
                }
            }, 1000); //启动1秒定时
        };
        var _reset = function (str) {
            _curTime = _maxTime;
            _isBlock = false;
            clearInterval(_timer);
            _$t.innerHTML = str || '获取';
        };
        var _isRunning = function () {
            return !(_curTime == _maxTime);
        };
        /*
         var _click = function (fn) {
         _target.click(function () {
         if (_isBlock) {
         return;
         }
         fn();
         });
         };*/
        return {
            start: _start,
            reset: _reset,
            isRunning: _isRunning,
            //click: _click
        };
    };
});