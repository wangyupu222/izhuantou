/*function fixedToBottom(a, b, c) {
    var d = $(window).height(),
    e = b.height(),
    f = a.height(),
    g = e + f;
    e > d - f ? a.removeClass(c) : d > g && a.addClass(c)
}
function periodFun() {
    var a = $(".inform-list .css-bar").find("strong");
    a.each(function(b) {
        var c, d, e = ($(this), $(".inform-list .css-bar").eq(b)),
        f = $(".inform-list .total-periods").eq(b),
        g = $(".inform-list .surplus-periods").eq(b),
        h = f.html(),
        i = g.html(),
        j = e.width();
        c = i * j / h,
        d = c / j * 100,
        a.eq(b).css("width", d + "%")
    })
}*/
/*function footerAutoFun(a, b, c) {
    var d = $(window).height(),
    e = b.height(),
    f = a.height(),
    g = e + f;
    e > d - f ? a.removeClass(c) : d > g && a.addClass(c)
}*/
/*$(function() {
    function a(a, b) {
        a.each(function(a, b) {
            var c = $(this),
            d = 3.6 * c.find("span").text();
            180 >= d ? c.find("div[data-type='version-project-right']").css("transform", "rotate(" + d + "deg)") : ($(this).find("div[data-type='version-project-right']").css("transform", "rotate(180deg)"), $(this).find("div[data-type='version-project-left']").css("transform", "rotate(" + (d - 180) + "deg)"))
        }),
        a.find("span[data-type='version-project-cer-span']").each(function(a) {
            var c = $(this).html();
            c > 0 && 50 >= c ? $(this).parents(b).css("backgroundColor", "#ffd200") : c > 50 && 100 > c ? $(this).parents(b).css("backgroundColor", "#ff510d") : 100 == c && $(this).parents(b).css("backgroundColor", "#81c931")
        })
    }
    var b = $(".version-project-record-list>p");
    b.find("a").hover(function() {
        var a = $(this);
        a.parent().addClass("z-index1")
    },
    function() {
        var a = $(this);
        a.parent().removeClass("z-index1")
    }),
    $("#login_name_2").on("click",
    function(a) {
        location.href = "https://www.baidu.com/"
    });
    var c = $(".footer"),
    d = $(".main-offsetHeight");
    c = "undefined" != typeof $(".footer")[0] ? $(".footer") : $("div[data-type='footer']"),
    fixedToBottom(c, d, "fixedToBottom"),
    $(window).resize(function() {
        fixedToBottom(c, d, "fixedToBottom")
    });
    var e = $("div[data-type='ver-project-circle']"),
    f = "div[data-type='ver-project-circle']";
    a(e, f)
});*/
/*var circleRender = function() {
    $(".circle").each(function(a, b) {
        var c = 3.6 * $(this).find("span").text();
        180 >= c ? $(this).find(".right").css("transform", "rotate(" + c + "deg)") : ($(this).find(".right").css("transform", "rotate(180deg)"), $(this).find(".left").css("transform", "rotate(" + (c - 180) + "deg)"))
    });
    var a = $(".mask > span");
    a.each(function(b) {
        var c = a.eq(b).html(),
        d = navigator.appName,
        e = navigator.appVersion,
        f = e.split(";");
        if (void 0 != f[1]) var g = f[1].replace(/[ ]/g, "");
        c >= 75 && 100 > c && ("Microsoft Internet Explorer" != d || "MSIE7.0" != g) ? $(this).parents(".circle").css("backgroundColor", "#ff2802") : c >= 50 && 100 > c && ("Microsoft Internet Explorer" != d || "MSIE7.0" != g) ? $(this).parents(".circle").css("backgroundColor", "#ff9102") : 100 != c || "Microsoft Internet Explorer" == d && "MSIE7.0" == g || $(this).parents(".circle").css("backgroundColor", "#6db344")
    })
};
$(function() {
    circleRender()
}),
$(function() {
    $(".tender-item"),
    $(".subtraction"),
    $(".quickly");
    $(document).on("mouseenter", ".tender-item",
    function() {
        $(this).addClass("tender-hover-bg")
    }),
    $(document).on("mouseleave", ".tender-item",
    function() {
        $(this).removeClass("tender-hover-bg")
    }),
    $(document).on("click", ".quickly",
    function() {
        for (var a = $(this).parents(".tender-item").index(), b = $(this).parents("#tender-items").find(".tender-item"), c = 0, d = b.length; d > c; c++) b.eq(c).removeClass("tender-active-bg");
        b.eq(a).addClass("tender-active-bg")
    });
    $("input[data-target='total']").val(),
    $(".plus"),
    $(".subtract"),
    $(".quickly-input"),
    $(".btn-submit");
    $(document).on("click", ".plus",
    function() {
        var a = $(this).parent().find(".quickly-input"),
        b = parseInt(a.val());
        b > 100 && (b -= 100, a.val(b)),
        100 >= b && $(this).addClass("displey-reduce")
    }),
    $(document).on("click", ".subtract",
    function() {
        var a = $(this).parent().find(".quickly-input"),
        b = parseInt(a.val());
        b += 100,
        a.val(b),
        $(this).parent().find(".plus").removeClass("displey-reduce")
    });
    var a = !1,
    b = $("#login_after");
    "none" != b.css("display") ? ($(".bid").addClass("btn-submit"), a = !1) : ($(".bid").removeClass("btn-submit"), a = !0),
    a && $(".bid").click(function() {
        alert("您还未登录，请登录后再操作")
    })
}),
$(function() {
    var a = $("#drop-list .drop-title");
    $(".drop-tender-box");
    a.on("click", "li",
    function() {
        for (var a = $(this).index(), b = $(this).parent().find("li"), c = $(this).parent().next().find(".tender-list"), d = 0, e = b.length; e > d; d++) b.eq(d).removeClass("drop-active"),
        c.eq(d).hide();
        $(this).addClass("drop-active"),
        c.eq(a).show()
    })
}),
$(function() {
    var a = $(".btn-minute-pull"),
    b = a.find("strong").html();
    $(document).on("click", ".btn-minute-pull",
    function() {
        var a = $(this),
        c = a.parent();
        c.toggleClass("btn-away-open"),
        c.parent().toggleClass("open-border"),
        "none" == c.parent().next().css("display") ? (c.parent().next().show(), a.find("strong").html("收起"), heightAuto()) : (c.parent().next().hide(), a.find("strong").html(b))
    })
}),
$(function() {
    periodFun()
}),
$(function() {
    var a = $(".backtop").parent(),
    b = $(".page-quick-bid").parent(),
    c = $(".page-download").parent();
    a.hide(),
    b.hide(),
    c.hide(),
    $(window).on("scroll",
    function() {
        var d = $(window).scrollTop();
        d > 50 ? (a.fadeIn(400), c.fadeIn(400)) : (a.fadeOut(200), c.fadeOut(400)),
        d > 510 ? b.fadeIn(400) : b.fadeOut(200)
    }),
    a.click(function() {
        $("body,html").animate({
            scrollTop: "0px"
        },
        800)
    })
}),
$(function() {
    var a, b, c, d, e = $(".btn-remove"),
    f = $(".btn-add"),
    g = $(".input-receive"),
    h = $(".available-money"),
    i = ($(".message-one"), $(".message-two"), $(".left-over-case"));
    $(".btn-investment");
    void 0 != h[0] && (c = h.html(), d = c.replace(",", ""), d = parseInt(d)),
    void 0 != i[0] && (a = i.html(), b = a.replace(",", ""), b = parseInt(b)),
    e.click(function() {
        var a = parseInt(g.val());
        if (a > 100) {
            var a = a - 100;
            g.val(a)
        }
        100 >= a && $(this).addClass("displey-reduce")
    }),
    f.click(function() {
        var a = parseInt(g.val()),
        a = a + 100;
        g.val(a),
        e.removeClass("displey-reduce")
    })
}),
$(function() {
    var a = $(".other-type");
    a.click(function() {
        var a = $(this).parents(".capital-aside").find(".toggle-type-list");
        "none" == a.css("display") ? (a.css("display", "block"), $(this).addClass("ico-pull").html("收起")) : "block" == a.css("display") && (a.css("display", "none"), $(this).removeClass("ico-pull").html("其他"))
    })
});
var showComLayer = function(a, b) {
    var c, d, e = $(window).width(),
    f = $(window).height(),
    g = ($("body").height(), $(document).height());
    a.css({
        width: e + "px",
        height: g + "px"
    }),
    c = (e - b.width()) / 2,
    d = (f - b.height()) / 2,
    b.css({
        left: c + "px",
        top: d + $(window).scrollTop() + "px",
        display: "block"
    }),
    a.show()
};
$(function() {
    function a() {
        for (var a = $(".btn-box-updown").children(), b = 0, c = a.length; c > b; b++) a.eq(b).removeClass("displey-reduce"),
        a.eq(b).find("span").removeClass("ico-down-failed"),
        a.eq(b).find("span").removeClass("ico-up-failed")
    }
    function b() {
        i.hide(),
        h.show()
    }
    function c(a, b) {
        b ? a.each(function(a) {
            var c = $(this);
            c.removeClass(b)
        }) : a.each(function(a) {
            var b = $(this);
            b.hide()
        })
    }
    var d = $(".bid"),
    e = ($(window).width(), $(window).height(), $(document).height(), $(".popup-mask")),
    f = $(".popup");
    $(".closed");
    d.on("click",
    function() {
        $(this).parent().find(".btn-submit")[0]
    }),
    $(".version-layer").hasClass("version-1") || (f.on("click", ".reload-closed",
    function() {
        window.location.reload(),
        $(this).parents(".popup").hide(),
        e.hide()
    }), $(document).on("click", ".reload-closed",
    function() {
        window.location.reload(),
        $(this).parents(".web-layer").hide(),
        e.hide()
    }), f.on("click", ".btn-return,.closed,.shut-down",
    function() {
        $(this).parents(".popup").hide(),
        e.hide()
    }), $(document).on("click", ".btn-return,.closed,.shut-down",
    function() {
        $(this).parents(".web-layer").hide(),
        e.hide()
    }));
    var g = $(".sum-input"),
    h = $(".deal-row"),
    i = $(".sign-errormsg");
    g.on("change",
    function() {
        var c = $(this),
        d = c.val();
        d = d.replace(/\s+/g, ""),
        c.val(d);
        var e = /^\d+(\.\d+)?$/;
        e.test(d) ? d > currentBalance ? (h.hide(), i.addClass("error-msg-red").html("余额不足，请先<a href='/myaccount/capital/deposit'>充值</a>").show()) : d > amountBidAval ? (h.hide(), i.addClass("error-msg-red").html("不能高于剩余可投金额（" + amountBidAval + "元）").show()) : 1 > d ? (h.hide(), i.addClass("error-msg-red").html("投资额不能低于1元").show()) : (b(), a()) : (h.hide(), i.addClass("error-msg-red").html("请输入整数").show())
    }),
    $(".btn-box-updown").on("click", ".btn-css-up",
    function() {
        a();
        var c = parseFloat(g.val()),
        d = [amountUserLimit, currentBalance, amountBidAval];
        d.sort(function(a, b) {
            return a - b
        });
        var e = d[0];
        c = (c + 100).toFixed(0),
        e > c ? (g.val(c), b()) : (g.val(e), $(this).addClass("displey-reduce"), $(this).find("span").addClass("ico-up-failed"))
    }),
    $(".btn-box-updown").on("click", ".btn-css-down",
    function() {
        a();
        var c = parseFloat(g.val()),
        d = [amountUserLimit, currentBalance, amountBidAval];
        d.sort(function(a, b) {
            return a - b
        });
        var e = d[0];
        c = (c - 100).toFixed(0),
        100 > c ? (g.val(100), $(this).addClass("displey-reduce"), $(this).find("span").addClass("ico-down-failed"), b()) : c > e ? (g.val(e), b()) : (g.val(c), b())
    });
    var j = $(".red-switch");
    j.on("click", "span",
    function() {
        var a = $(this),
        b = a.index();
        c(j.find("span"), "blue-current"),
        c(a.parent().next().children()),
        a.addClass("blue-current"),
        j.next().children().eq(b).show()
    })
}),
$(function() {
    var a = $(".nav-list > li");
    a.find("a").mouseover(function() {
        var a = $(this).parent();
        a.addClass("nav-li-hover");
        var b = $(this).parents(".nav-list").find(".nav-hover");
        b.css({
            width: a.width() + "px"
        });
        var c = $(this).offset().left - $(".nav-list").offset().left;
        b.stop().animate({
            left: c - 60 + "px"
        },
        200)
    }),
    a.find("a").mouseout(function() {
        var a = $(this).parent(),
        b = $(this).parents(".nav-list").find(".nav-hover");
        a.removeClass("nav-li-hover"),
        b.css({
            width: "0"
        })
    })
}),
$(function() {
    var a = $(".paging");
    a.on("mouseenter", "a",
    function() {
        $(this).addClass("digital-hover")
    }).end().on("mouseleave", "a",
    function() {
        $(this).removeClass("digital-hover")
    })
}),
$(function() {
    function a() {
        d++,
        d >= b.length && (d = 1, c.css({
            left: "0px"
        })),
        c.stop().animate({
            left: -b.width() * d
        },
        1e3),
        window.setTimeout(a, 2e3)
    }
    $(".gallery-ul").first().clone().appendTo($(".gallery-xs"));
    var b = $(".gallery-ul"),
    c = $(".gallery-xs");
    c.width(b.width() * b.length);
    var d = 0;
    a()
}),
$(function() {
    var a = $(".borrowing-tab > span");
    a.on("click",
    function() {
        for (var a = $(this).index(), b = $(this).parents(".aside-right").find(".invest-main"), c = 0, d = b.length; d > c; c++) b.eq(c).hide(),
        $(".borrowing-tab > span").eq(c).removeClass("headline-current");
        $(this).addClass("headline-current"),
        b.eq(a).show()
    })
}),
$(function() {}),
$(function() {
    var a = $(".choose-row .bank-item>label");
    a.on("click",
    function() {
        for (var a = $(this).parent().index(), b = $(".information>.remarks>div"), c = 0, d = b.length; d > c; c++) b.eq(c).hide(),
        $(".rechargebankst-cft-infor").hide();
        "tenpay" === $(this).find("input").val() ? $(".rechargebankst-cft-infor").show() : b.eq(a).show()
    })
}),
$(function() {
    $(".span-radio").on("click", "input",
    function() {
        for (var a = ($(this).parents(".repay-left").next().find(".press-button-row"), $(this).parents(".repay-left").next().find("money-row"), $(this).parents(".repay-left").next().find(".also-deal")), b = $(this).parents(".repay-row").find(".also-deal"), c = 0, d = b.length; d > c; c++) b.eq(c).hide();
        "disabled" != $(this).attr("disabled") && a.show()
    });
    for (var a = $(".repaymoney-row"), b = 0, c = a.length; c > b; b++)"block" == a.eq(b).find(".press-button-row").css("display") && a.eq(b).find("input[data-type=radio]").attr("disabled", "disabled")
}),
$(function() {
    var a = $(".form-group").find("p[data-toggle=onfocus]"),
    b = $(".bank-list");
    a.on("click",
    function(a) {
        var b = $(this);
        b.find("small").text("▲"),
        b.addClass("border-radius"),
        b.parent().next().show(),
        a.stopPropagation()
    }),
    $(document).on("click",
    function() {
        b.hide(),
        a.find("small").text("▼"),
        a.removeClass("border-radius")
    }),
    b.on("click", "a",
    function() {
        var a = $(this),
        b = a.find("img").clone(),
        c = a.parent().prev().find("p");
        c.find("img").remove(),
        c.append(b),
        a.parent().hide(),
        c.removeClass("border-radius")
    });
    var c = $(".form-group").find("input[data-toggle=mapping]");
    c.on("keyup",
    function() {
        var a = $(this),
        b = a.val(),
        c = a.parent().find(".just-layer");
        c.show(),
        c.html(b),
        "" == b && c.hide()
    }),
    c.on("blur",
    function() {
        var a = $(this),
        b = a.parent().find(".just-layer");
        b.hide()
    }),
    c.on("focus",
    function() {
        var a = $(this),
        b = a.val();
        if ("" != b) {
            var c = a.parent().find(".just-layer");
            c.show()
        }
    })
}),
$(function() {
    var a = ($(".bank-event"),
    function() {
        $("#submitBtn").addClass("btn")
    }),
    b = function() {
        $("#submitBtn").removeClass("btn-default-color"),
        $("#submitBtn").removeAttr("disabled")
    };
    a(),
    $(document).on("click", ".bank-event",
    function() {
        var c = $(this);
        if (c.hasClass("cash-card-click")) c.removeClass("cash-card-click");
        else {
            for (var d = $(".bank-event"), e = 0, f = d.length; f > e; e++) d.eq(e).removeClass("cash-card-click");
            c.addClass("cash-card-click")
        }
        $(".cash-card-click").length <= 0 ? a() : b()
    });
    var c = $(".defautl-add-card");
    c.on("mouseenter",
    function() {
        $(this).addClass("hover-bgcolor")
    }),
    c.on("mouseleave",
    function() {
        $(this).removeClass("hover-bgcolor")
    })
}),*/
$(function() {
    /*function a() {
        var a = window.location.href,
        b = parseInt(a.substring(a.lastIndexOf("#") + 1));
        if (b = isNaN(b) ? "": b, "" != b) {
            var c = $(".helpc-listings-item-title"),
            d = c.filter("#" + b);
            if (0 == d.length) return;
            for (var e = c.next(), f = 0, g = e.length; g > f; f++) e.eq(f).hide(),
            c.eq(f).removeClass("current");
            d.addClass("current").next().show()
        }
    }
    function b(a, b) {
        b ? a.each(function() {
            $(this).removeClass(b)
        }) : a.each(function() {
            $(this).hide()
        })
    }
    function c(a, b) {
        b ? a.each(function(a) {
            var c = $(this);
            c.removeClass(b)
        }) : a.each(function(a) {
            var b = $(this);
            b.hide()
        })
    }
    function d(a, b, c, d, e) {
        var f = a.attr("data-type");
        void 0 != f && (a.val() == f || "" == a.val() ? (a.val(f).css("color", c), e && a.removeClass(e)) : (a.css("color", d), e && a.addClass(e)), a.removeClass(b))
    }
    function e(a, b, c) {
        var d = a.attr("data-type");
        if (void 0 != d) {
            var e = a.val().replace(/\s+/g, "");
            e == d && a.val("").css("color", c),
            a.addClass(b)
        }
    }
    function f(a, b, c, e, f) {
        a.each(function(a, g) {
            var h = $(this);
            d(h, b, c, e, f)
        })
    }
    function g(a, b) {
        a.hover(function() {
            $(this).addClass(b)
        },
        function() {
            $(this).removeClass(b)
        })
    }
    function h() {
        $('div[data-type="verxon2-circle"]').each(function(a, b) {
            var c = 3.6 * $(this).find("span").text();
            180 >= c ? $(this).find(".verxon2-right").css({
                transform: "rotate(" + c + "deg)",
                "-webkit-transform": "rotate(" + c + "deg)"
            }) : ($(this).find(".verxon2-right").css({
                transform: "rotate(180deg)",
                "-webkit-transform": "rotate(180deg)"
            }), $(this).find(".verxon2-left").css({
                transform: "rotate(" + (c - 180) + "deg)",
                "-webkit-transform": "rotate(" + (c - 180) + "deg)"
            }))
        }),
        $("span[data-type='cerxon2-span']").each(function(a) {
            var b = $(this).html();
            b > 0 && 50 >= b ? $(this).parents('div[data-type="verxon2-circle"]').css("backgroundColor", "#ffd200") : b > 50 && 100 > b ? $(this).parents('div[data-type="verxon2-circle"]').css("backgroundColor", "#ff510d") : 100 == b && $(this).parents('div[data-type="verxon2-circle"]').css("backgroundColor", "#81c931")
        })
    }
    var i = $(".jobs-title");
    i.on("click",
    function() {
        var a = $(this),
        b = a.next();
        if ("block" == b.css("display")) b.hide(),
        a.find("em").removeClass("ico-cms-show");
        else if ("none" == b.css("display")) {
            for (var c = $(".personnel-list").find(".jobs-infor"), d = 0, e = c.length; e > d; d++) c.eq(d).css("display", "none"),
            $(".jobs-title").eq(d).find("em").removeClass("ico-cms-show");
            b.show(),
            a.find("em").addClass("ico-cms-show")
        }
        footerAutoFun($(".footer"), $(".main-offsetHeight"), "fixedToBottom")
    }),
    a(),
    $(".jobs-title").mouseenter(function() {
        $(this).addClass("jobs-hover")
    }).mouseleave(function() {
        $(this).removeClass("jobs-hover")
    });*/
    /*var j = $(".account-msg-click"),
    k = $(".accont-show-infor"),
    l = j.find(".account-msg-action>strong").html();
    j.flag = 1,
    j.on("click",
    function() {
        if (1 == j.flag) {
            var a = $(this),
            c = a.find(".account-msg-action>strong");
            "block" == a.next().css("display") ? (a.next().hide(), c.html(l), a.find(".account-msg-action>span").removeClass("ico-arrow-msg-bottom"), a.removeClass("account-msg-bg-none")) : "none" == a.next().css("display") && (a.next().show(), j.find(".account-msg-action>strong").html(l), b(k), b(j, "account-msg-bg-none"), b(j.find(".account-msg-action>span"), "ico-arrow-msg-bottom"), c.html("收起"), a.next().show(), a.addClass("account-msg-bg-none"), a.find(".account-msg-action>span").addClass("ico-arrow-msg-bottom"), a.find("p").removeClass("account-msg-theme"))
        }
        j.flag = 1
    }),
    j.on("mouseenter",
    function() {
        var a = $(this);
        a.addClass("account-msg-hover")
    }),
    j.on("mouseleave",
    function() {
        var a = $(this);
        a.removeClass("account-msg-hover")
    }),
    j.find("input[type='checkbox']").on("click",
    function() {
        j.flag = 0
    });
    var m = $("a[data-type='all-checkbox']");
    m.find("input[type=checkbox]").on("click",
    function() {
        $(".information").find("input[type=checkbox]").prop("checked", this.checked)
    });
    var n = $(".input-blur-focus");
    n.find(":input").blur(function() {
        var a = $(this),
        b = a[0].parents;
        "" == a.val() && (a.val(b), a.css("color", "#ccc"))
    }).focus(function() {
        var a = $(this),
        b = a.attr("data-value");
        a[0].parents = b,
        a.val() == b && (a.val(""), a.css("color", "#666"))
    });
    var o = $("#sidebar-weixin");
    o.on("mouseenter",
    function() {
        $(".qr-codes").show()
    }),
    o.on("mouseleave",
    function() {
        $(".qr-codes").hide()
    });
    var p = $("#sidebar-top");
    p.hide(),
    p.click(function() {
        $("body,html").animate({
            scrollTop: "0px"
        },
        800)
    }),
    $(window).on("scroll",
    function() {
        var a = $(window).scrollTop();
        a > 50 ? p.fadeIn(400) : p.fadeOut(200)
    });
    var q = $("#home-pop-closed");
    q.on("click",
    function() {
        var a = $(this);
        a.parents("#home-pop").hide()
    });
    var r = $("span[data-type='proin-title']");
    r.on("click", "a",
    function() {
        var a = $(this),
        b = a.index(),
        d = $("div[data-type='proin-talbe']").children();
        c(r.find("a"), "current"),
        a.addClass("current"),
        c(d),
        d.eq(b).show()
    });*/
  /*  var s = $("div[data-type='version-project-absolute-row']");
    s.children().on("click",
    function() {
        var a = $(this),
        c = a.index();
        b(s.children(), "current"),
        a.addClass("current"),
        b($("div[data-type='version-project-table']").children()),
        a.parent().parent().next("div[data-type='version-project-table']").children().eq(c).show();
        var d = $(".footer"),
        e = $(".main-offsetHeight");
        d = "undefined" != typeof $(".footer")[0] ? $(".footer") : $("div[data-type='footer']"),
        fixedToBottom(d, e, "fixedToBottom")
    });*/
    var t = $("span[data-type='financial-cal-menu-title']");
    t.on("click",
    function() {
        var a = $(this);
        a.find("em").toggleClass("current"),
        a.next("div").slideToggle()
    }),
    t.on("mouseenter",
    function() {
        $(this).addClass("hover")
    }),
    t.on("mouseleave",
    function() {
        $(this).removeClass("hover")
    });
   /* var u = $("div[data-type='financial-cal-textbox']");
    f(u.find(":input[type='text']"), "focus", "#999", "#333", "default-arial"),
    u.find(":input[type='text']").on("blur",
    function() {
        var a = $(this);
        d(a, "focus", "#999", "#333", "default-arial")
    }),
    u.find(":input[type='text']").on("focus",
    function() {

        var a = $(this);
        e(a, "focus", "#333")
    });*/
    /* var v = $("div[data-type='details-item']");
    v.on("mouseenter", ".item-list",
    function() {
        var a = $(this);
        a.addClass("item-list-hover-bg")
    }),
    v.on("mouseleave", ".item-list",
    function() {
        var a = $(this);
        a.removeClass("item-list-hover-bg")
    }),
    v.on("click", ".item-list",
    function(a) {});
    var w = $("div[data-type='introduction']"),
    x = $("div[data-type='faith']");
    g(w, "the-introduction-hover"),
    g(x, "the-faith-hover"),
    h();
    var y = $("div[data-type='newbie-title']");
    y.find("a").on("click",
    function() {
        var a = $(this);
        y.find("a").each(function() {
            $(this).removeClass("current")
        }),
        a.addClass("current");
        var b = y.parent().parent().next("div").children();
        b.each(function() {
            $(this).hide()
        }),
        b.eq(a.index()).show()
    });
   var z = $("span[data-type='helpc-list-item-title']"),
    A = $("div[data-type='helpc-listings-box']");
    z.on("click",
    function() {
        var a = $(this),
        b = a.next("div");
        "block" == b.css("display") ? (a.removeClass("current"), b.hide()) : "none" == b.css("display") && (A.find("span[data-type='helpc-list-item-title']").removeClass("current").end().find("div[data-type='helpc-listings-item-infor']").hide(), b.show(), a.addClass("current"))
    }),
    z.hover(function() {
        $(this).addClass("hover")
    },
    function() {
        $(this).removeClass("hover")
    }),
    $("div[data-type='tail-ton-box']").find("a").on("mouseenter",
    function() {
        $(this).find("span").fadeIn(150)
    }),
    $("div[data-type='tail-ton-box']").find("a").on("mouseleave",
    function() {
        $(this).find("span").fadeOut(150)
    });*/
    /*var B = $("div[data-type='cargotong-action']"),
    C = $("div[data-type='cargotong-picture-box']");
    B.children().on("mouseenter",
    function() {
        var a = $(this),
        b = a.index();
        B.children().each(function() {
            var a = $(this);
            a.removeClass("hover")
        }),
        C.children().each(function() {
            var a = $(this);
            a.hide()
        }),
        a.addClass("hover"),
        C.children().eq(b).show()
    });
    var D = $("span[data-type='copagency-tab-title']"),
    E = $("div[data-type='copagency-tab-box']");
    D.on("click", "a",
    function() {
        var a = $(this),
        b = a.index();
        D.find("a").each(function() {
            $(this).removeClass("current")
        }),
        E.children().each(function() {
            $(this).hide()
        }),
        a.addClass("current"),
        E.children().eq(b).show()
    })*/
});
/*var contentEqMask = function() {
    $("div[data-type='version-popup-mobel']").each(function() {
        var a = $(this),
        b = a.find("div[data-type='version-popup-main']").height();
        a.find("div[data-type='version-popup-mask']").height(parseInt(b) + 16)
    })
};*/