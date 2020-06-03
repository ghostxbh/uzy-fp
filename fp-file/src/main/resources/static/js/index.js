/**
 * Create by ghostxbh 2020/6/3
 */

//图片上传
function uploadImage(fileDom) {
    //获取文件
    var file = fileDom.files[0];
    //判断类型
    var imageType = /^image\//;
    if (file === undefined || !imageType.test(file.type)) {
        $("#btn-ok").unbind("click").click(function () {
            $("#modalDiv").modal("hide");
        });
        $(".modal-body").text("请选择图片！");
        $('#modalDiv').modal();
        return;
    }
    //判断大小
    if (file.size > 10240000) {
        $("#btn-ok").unbind("click").click(function () {
            $("#modalDiv").modal("hide");
        });
        $(".modal-body").text("图片大小不能超过1M！");
        $('#modalDiv').modal();
        return;
    }
    var ul = $(fileDom).parents(".details_picList");
    var type;
    if (ul.attr("id") === "product_single_list") {
        type = "single";
    } else {
        type = "details";
    }
    //清空值
    $(fileDom).val('');
    var formData = new FormData();
    formData.append("file", file);
    //上传图片
    $.ajax({
        url: "/mall/admin/uploadProductImage",
        type: "post",
        data: formData,
        contentType: false,
        processData: false,
        dataType: "json",
        mimeType: "multipart/form-data",
        success: function (data) {
            $(fileDom).attr("disabled", false).prev("span").text("上传图片");
            if (data.success) {
                if (type === "single") {
                    $(fileDom).parent('.details_picList_fileUpload').before("<li><img src='" + data.fileUrl + "' width='128px' height='128px' name='new'/></li>");
                    checkFileUpload(ul, 5);
                } else {
                    $(fileDom).parent('.details_picList_fileUpload').before("<li><img src='" + data.fileUrl + "' width='128px' height='128px' name='new'/></li>");
                    checkFileUpload(ul, 8);
                }
            } else {
                alert("图片上传异常！");
            }
        },
        beforeSend: function () {
            $(fileDom).attr("disabled", true).prev("span").text("图片上传中...");
        },
        error: function () {

        }
    });
}