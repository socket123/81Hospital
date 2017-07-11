(function($) {
    var ms = {
        init: function(obj, args) {
            return (function() {
                ms.fillHtml(obj, args);
                ms.bindEvent(obj, args);
            })();
        },
        //填充html
        fillHtml: function(obj, args) {
            return (function() {
            	var currentIndex = parseInt(args.current);
            	var pageCount = parseInt(args.pageCount);
                obj.empty();
                //上一页
                if (currentIndex > 1) {
                    obj.append('<a href="javascript:;" class="homePage">首页</a>');
                    obj.append('<a href="javascript:;" class="prevPage">上一页</a>');
                } else {
                    obj.remove('.prevPage');
                    obj.append('<span class="disabled">首页</span>');
                    obj.append('<span class="disabled">上一页</span>');
                }
                //中间页码
                var start = currentIndex,
                end = currentIndex;
                if ((start > 1 && pageCount < 8) || currentIndex == 1) {
                    end++;
                }
                if (currentIndex > pageCount - 8 && currentIndex >= pageCount) {
                    start--;
                }
                for (; start <= end; start++) {
                    if (start <= pageCount && start >= 1) {
                        if (start != currentIndex) {} else {
                            obj.append('<span class="current">' + start + '</span>');
                        }
                    }
                }
                //下一页
                if (currentIndex < pageCount) {
                    obj.append('<a href="javascript:;" class="nextPage">下一页</a>');
                } else {
                    obj.remove('.nextPage');
                    obj.append('<span class="disabled">下一页</span>');
                }

                //尾页
                if (pageCount <= 1) {
                    obj.append('<span class="disabled">尾页</span>');
                } else if (currentIndex == pageCount) {
                    obj.append('<span class="disabled">尾页</span>');

                } else {
                    obj.append('<a href="javascript:;" class="lastPage">尾页</a>');

                }
                obj.append('<input id="pageNumSelect" type="text" onkeyup="value=value.replace(/[^\\d]/g,\'\')"  onafterpaste="value=value.replace(/[^\\d]/g,\'\')"/>');
                obj.append('<a href="javascript:;" class="goToPage">前往</a>');
                obj.append("<span class='totalPageCount'>共" + pageCount + "页</span>");

            })();
        },
        //绑定事件
        bindEvent: function(obj, args) {
            return (function() {
                obj.on("click", "a.goToPage",
                function() {
                    var pageNum = $("#pageNumSelect").val();
                    if (pageNum == null || pageNum == '' || pageNum == undefined) {
                        pageNum = 1;
                    }
                    var current = parseInt(pageNum);
                    if (current == 0) {
                        current = 1;
                        ms.fillHtml(obj, {
                            "current": current,
                            "pageCount": args.pageCount
                        });
                        if (typeof(args.backFn) == "function") {
                            args.backFn(current);
                        }
                    } else if (current > args.pageCount) {
                        layer.alert("页数大于当前的最大页");
                    } else {
                        ms.fillHtml(obj, {
                            "current": current,
                            "pageCount": args.pageCount
                        });
                        if (typeof(args.backFn) == "function") {
                            args.backFn(current);
                        }

                    }
                });

                obj.on("click", "a.tcdNumber",
                function() {
                    var current = parseInt($(this).text());
                    ms.fillHtml(obj, {
                        "current": current,
                        "pageCount": args.pageCount
                    });
                    if (typeof(args.backFn) == "function") {
                        args.backFn(current);
                    }
                });
                //上一页
                obj.on("click", "a.prevPage",
                function() {
                    var current = parseInt(obj.children("span.current").text());
                    ms.fillHtml(obj, {
                        "current": current - 1,
                        "pageCount": args.pageCount
                    });
                    if (typeof(args.backFn) == "function") {
                        args.backFn(current - 1);
                    }
                });
                //下一页
                obj.on("click", "a.nextPage",
                function() {
                    var current = parseInt(obj.children("span.current").text());
                    ms.fillHtml(obj, {
                        "current": current + 1,
                        "pageCount": args.pageCount
                    });
                    if (typeof(args.backFn) == "function") {
                        args.backFn(current + 1);
                    }
                });
                //首页
                obj.on("click", "a.homePage",
                function() {
                    var current = parseInt(obj.children("span.current").text());
                    ms.fillHtml(obj, {
                        "current": 1,
                        "pageCount": args.pageCount
                    });
                    if (typeof(args.backFn) == "function") {
                        args.backFn(1);
                    }
                });
                //尾页
                obj.on("click", "a.lastPage",
                function() {
                    var current = parseInt(obj.children("span.current").text());
                    ms.fillHtml(obj, {
                        "current": args.pageCount,
                        "pageCount": args.pageCount
                    });
                    if (typeof(args.backFn) == "function") {
                        args.backFn(args.pageCount);
                    }
                });
            })();
        }
    }
    $.fn.createPage = function(options) {
        var args = $.extend({
            pageCount: 10,
            current: 1,
            backFn: function() {}
        },
        options);
        ms.init(this, args);
    }
})(jQuery);