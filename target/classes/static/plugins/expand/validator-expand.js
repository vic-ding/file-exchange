$(function () {
    // 节点管理的校验
    $("#mainForm").validate({
        rules: {
            nodeCode: {
                required: true,
                minlength: 3,
                maxlength: 18
                , remote: { //这里是验证用户名是否重复
                    type: "post",
                    url: ctx + 'gx/node/check/nodeCode',
                    data: {
                        nodeCode: function () {
                            return $("#nodeCode").val();
                        }
                    },
                    dataType: "html",
                    dataFilter: function (data) {
                        if (data == "true")
                            return true;
                        else
                            return false;
                    }
                }
            }
        },
        messages: {
            nodeCode: {
                remote: "&nbsp;&nbsp;*编码已存在"
            }
        }
    });

})
;