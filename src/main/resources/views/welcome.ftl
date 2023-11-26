<!DOCTYPE html>
<html>
<head>
    <#include "common.ftl">
</head>

<body class="childrenBody">

    <div class="layui-tab-item layui-show">
        <div class="layui-carousel" id="test10" align="center">
            <div carousel-item="">
                <div><img src="${ctx}/images/timg.jpeg" style="width:100%; height: 100%; object-fit: contain;"></div>
            </div>
        </div>
    </div>

    <script>
        layui.use(['carousel', 'form'], function () {
            var carousel = layui.carousel
                , form = layui.form;

            carousel.render({
                elem: '#test1'
                , arrow: 'always'
            });

            carousel.render({
                elem: '#test2'
                , interval: 1800
                , anim: 'fade'
                , height: '120px'
            });

            var ins3 = carousel.render({
                elem: '#test3'
            });
            carousel.render({
                elem: '#test10'
                , width: '100%'
                , height: '660px'
                , interval: 3000
            });

            carousel.on('change(test4)', function (res) {
                console.log(res)
            });

            var $ = layui.$, active = {
                set: function (othis) {
                    var THIS = 'layui-bg-normal'
                        , key = othis.data('key')
                        , options = {};

                    othis.css('background-color', '#5FB878').siblings().removeAttr('style');
                    options[key] = othis.data('value');
                    ins3.reload(options);
                }
            };

            form.on('switch(autoplay)', function () {
                ins3.reload({
                    autoplay: this.checked
                });
            });

            $('.demoSet').on('keyup', function () {
                var value = this.value
                    , options = {};
                if (!/^\d+$/.test(value)) return;

                options[this.name] = value;
                ins3.reload(options);
            });

            $('.demoTest .layui-btn').on('click', function () {
                var othis = $(this), type = othis.data('type');
                active[type] ? active[type].call(this, othis) : '';
            });
        });
    </script>
</body>
</html>
