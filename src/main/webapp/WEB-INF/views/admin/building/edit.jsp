<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url var = "buildingAPI" value ="/api/building"/>
<c:url var ="buildingEditURL" value="/admin/building-edit"/>
<html>
<head>
    <c:if test="${empty buildingId}">
        <title>Thêm toà nhà</title>
    </c:if>
    <c:if test="${not empty buildingId}">
        <title>Chỉnh sửa toà nhà</title>
    </c:if>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href='<c:url value="/admin/home" />'>Home</a>
                </li>
                <c:if test="${empty buildingId}">
                    <li class="active">Thêm toà nhà</li>
                </c:if>
                <c:if test="${not empty buildingId}">
                    <li class="active">Chỉnh sửa toà nhà</li>
                </c:if>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <c:if test="${null != messageResponse}">
                    <div class="alert alert-block alert-${alert}">
                        <button type="button" class="close" data-dismiss="alert">
                            <em class="ace-icon fa fa-times"></em>
                        </button>
                            ${messageResponse}
                    </div>
                </c:if>

                <div class="col-xs-12">
                    <form:form modelAttribute="building" id="formEdit" cssClass="form-horizontal">
                        <div class="form-group">
                            <div class="col-xs-9">
                                <form:hidden path="id" id="buildingId" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="name">Tên toà nhà</label>
                            <div class="col-xs-9">
                                <form:input path="name" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="district">Quận</label>
                            <div class="col-xs-9">
                                <form:select path="district" cssClass="chosen-select">
                                    <form:option value="">--- Chọn Quận ---</form:option>
                                    <form:options items="${districts}" />
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="ward">Phường</label>
                            <div class="col-xs-9">
                                <form:input path="ward" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="street">Đường</label>
                            <div class="col-xs-9">
                                <form:input path="street" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="structure">Kết cấu</label>
                            <div class="col-xs-9">
                                <form:input path="structure" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="numberOfBasement">Số tầng hầm</label>
                            <div class="col-xs-9">
                                <form:input path="numberOfBasement" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="floorArea">Diện tích sàn</label>
                            <div class="col-xs-9">
                                <form:input path="floorArea" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="direction">Hướng</label>
                            <div class="col-xs-9">
                                <form:input path="direction" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="level">Hạng</label>
                            <div class="col-xs-9">
                                <form:input path="level" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="rentArea">Diện tích thuê</label>
                            <div class="col-xs-9">
                                <form:input path="rentArea" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="rentAreaDescription">Mô tả diện tích</label>
                            <div class="col-xs-9">
                                <form:textarea path="rentAreaDescription" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="rentPrice">Giá thuê</label>
                            <div class="col-xs-9">
                                <input type="number" name="rentPrice" id="rentPrice" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="rentPriceDescription">Mô tả giá</label>
                            <div class="col-xs-9">
                                <form:input path="rentPriceDescription" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="serviceFee">Phí dịch vụ</label>
                            <div class="col-xs-9">
                                <form:input path="serviceFee" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="carFee">Phí ô tô</label>
                            <div class="col-xs-9">
                                <form:input path="carFee" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="motoFee">Phí mô tô</label>
                            <div class="col-xs-9">
                                <form:input path="motoFee" cssClass="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="overtimeFee">Phí ngoài giờ</label>
                            <div class="col-xs-9">
                                <form:input path="overtimeFee" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="waterFee">Tiền nước</label>
                            <div class="col-xs-9">
                                <form:input path="waterFee" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="electricityFee">Tiền điện</label>
                            <div class="col-xs-9">
                                <form:input path="electricityFee" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="deposit">Đặt cọc</label>
                            <div class="col-xs-9">
                                <form:input path="deposit" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="payment">Thanh toán</label>
                            <div class="col-xs-9">
                                <form:input path="payment" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="rentTime">Thời gian thuê</label>
                            <div class="col-xs-9">
                                <form:input path="rentTime" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="decorationTime">Thời gian trang trí</label>
                            <div class="col-xs-9">
                                <form:input path="decorationTime" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="managerName">Tên quản lý</label>
                            <div class="col-xs-9">
                                <form:input path="managerName" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="managerPhone">SĐT quản lý</label>
                            <div class="col-xs-9">
                                <form:input path="managerPhone" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="brokerageFee">Phí môi giới</label>
                            <div class="col-xs-9">
                                <form:input path="brokerageFee" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right">Loại toà nhà </label>
                            <div class="col-xs-9">
                                <c:forEach var="item" items="${buildingTypeMap}">
                                    <label class="checkbox-inline">
                                        <form:checkbox path="types" value="${item.key}"/>${item.value}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="note">Ghi chú</label>
                            <div class="col-xs-9">
                                <form:textarea path="note" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="linkOfBuilding">Link sản phẩm</label>
                            <div class="col-xs-9">
                                <form:input path="linkOfBuilding" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3 no-padding-right" for="map">Bản đồ</label>
                            <div class="col-xs-9">
                                <form:input path="map" cssClass="form-control" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 no-padding-right">Hình đại diện</label>
                            <input class="col-sm-3 no-padding-right" type="file" id="uploadImage"/>
                            <div class="col-sm-9">
                                <c:if test="${not empty building.image}">
                                    <c:set var="imagePath" value="/repository${building.image}"/>
                                    <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                </c:if>
                                <c:if test="${empty building.image}">
                                    <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                </c:if>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"></label>
                            <div class="col-xs-9">
                                <c:if test="${not empty building.id}">
                                    <input id="btnSave" type="button" class="btn btn-primary" value="Cập nhật" />
                                </c:if>
                                <c:if test="${empty building.id}">
                                    <input id="btnSave" type="button" class="btn btn-primary" value="Thêm" />
                                </c:if>
                                <input id="btnCancel" type="button" class="btn btn-warning" value="Huỷ" />
                                <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                            </div>
                        </div>
                    </form:form>
                </div>
            </div><!-- /.row -->
        </div> <!-- PAGE CONTENT ENDS -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->
<script>
    $("#btnSave").click(function (e) {
        e.preventDefault();
        let title;
        let message;
        let data = {};
        let buildingTypes = [];
        const formData = $("#formEdit").serializeArray();
        $.each(formData, function (index, v) {
            if ('types' === v.name) {
                buildingTypes.push(v.value);
            } else {
                if ('' !== imageBase64) {
                    data['imageBase64'] = imageBase64;
                    data['imageName'] = imageName;
                }
                data["" + v.name + ""] = v.value;
            }
        });
        $('#loading_image').show();
        data['types'] = buildingTypes;

        let id= $('#buildingId').val();

        if ('' === id) {
            title = 'Thêm';
            message = 'Thêm tòa nhà mới?';
        } else {
            title = 'Cập nhật';
            message = 'Cập nhật tòa nhà này?';
        }

        showMessageConfirmation(function () {
            if ('' === id) {
                addBuilding(data);
            } else {
                updateBuilding(data,id);
            }
        }, title, message)
    });

    function addBuilding(data){
        $.ajax({
            url: '${buildingAPI}',
            type: 'POST',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                $('#loading_image').hide();
                window.location.href = "${buildingEditURL}-" + response.id + "?message=insert_success";
            },
            error: function () {
                $('#loading_image').hide();
                showNotification('error', 'Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.');
            }
        });
    }

    function updateBuilding(data){
        $.ajax({
            url: '${buildingAPI}',
            type: 'PUT',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json',
            success: function (response) {
                $('#loading_image').hide();
                window.location.href = "${buildingEditURL}-" + response.id + "?message=update_success";
            },
            error: function () {
                showNotification('error', 'Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.');
            }
        });
    }

    let imageBase64 = '';
    let imageName = '';

    $('#uploadImage').change(function (event) {
        let reader = new FileReader();
        let file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. vd: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#btnCancel").click(function () {
        let id= $('#buildingId').val();

        if ('' !== id) {
            showAlertBeforeCanceling("/admin/building-list");
        }
    });
</script>
</body>
</html>
