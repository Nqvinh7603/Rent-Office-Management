<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="buildingEditURL" value="/admin/building-edit"/>
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
                    <form:form modelAttribute="building" cssClass="form-horizontal" id="formEdit" >
                        <div class="form-group">
                        <form:hidden path="id" id="buildingId" cssClass="form-control"/>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="name"> Tên tòa nhà </label>
                            <div class="col-sm-9">
                                <form:input path="name" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Quận </label>
                            <div class="col-sm-9">
                                <form:select path="district" cssClass="chosen-select">
                                    <form:option value="">--- Chọn Quận ---</form:option>
                                    <form:options items="${districts}"/>
                                </form:select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Phường </label>
                            <div class="col-sm-9">
                                <form:input path="ward" cssClass="form-control"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Đường </label>
                            <div class="col-sm-9">
                                <form:input path="street" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Kết cấu </label>
                            <div class="col-sm-9">
                                <form:input path="structure" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Số tầng
                                hầm </label>
                            <div class="col-sm-9">
                                <form:input path="numberOfBasement" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Diện tích sàn </label>
                            <div class="col-sm-9">
                                <form:input path="floorArea" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Hướng </label>
                            <div class="col-sm-9">
                                <form:input path="direction" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Hạng </label>
                            <div class="col-sm-9">
                                <form:input path="level" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Diện tích thuê </label>
                            <div class="col-sm-9">
                                <form:input path="rentArea" cssClass="form-control"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Mô tả diện tích </label>
                            <div class="col-sm-9">
                                <form:input path="rentAreaDescription" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Gía thuê </label>
                            <div class="col-sm-9">
                                <input type="number" name="rentPrice" id="rentPrice" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Mô tả giá </label>
                            <div class="col-sm-9">
                                <form:input path="rentPriceDescription" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Phí dịch vụ </label>
                            <div class="col-sm-9">
                                <form:input path="serviceFee" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Phí ô tô </label>
                            <div class="col-sm-9">
                                <form:input path="carFee" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Phí ô tô </label>
                            <div class="col-sm-9">
                                <form:input path="motoFee" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Phí ngoài giờ </label>
                            <div class="col-sm-9">
                                <form:input path="overtimeFee" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Tiền điện </label>
                            <div class="col-sm-9">
                                <form:input path="electricityFee" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Tiền nước </label>
                            <div class="col-sm-9">
                                <form:input path="waterFee" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Đặt cọc </label>
                            <div class="col-sm-9">
                                <form:input path="deposit" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Thanh toán </label>
                            <div class="col-sm-9">
                                <form:input path="payment" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Thời gian trang trí </label>
                            <div class="col-sm-9">
                                <form:input path="decorationTime" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Tên quản lí </label>
                            <div class="col-sm-9">
                                <form:input path="managerName" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Số điện thoại quản lý </label>
                            <div class="col-sm-9">
                                <form:input path="managerPhone" cssClass="form-control"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Phí môi giới </label>
                            <div class="col-sm-9">
                                <form:input path="brokerageFee" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Loại tòa nhà </label>
                            <div class="col-sm-9">
                                <c:forEach var="item" items="${buildingTypes}">
                                    <label class="checkbox-inline">
                                        <form:checkbox path="types" value="${item.key}"/>${item.value}
                                    </label>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Ghi chú </label>
                            <div class="col-sm-9">
                                <form:input path="note" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Link sản phẩm </label>
                            <div class="col-sm-9">
                                <form:input path="linkOfBuilding" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Bản đồ </label>
                            <div class="col-sm-9">
                                <form:input path="map" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <c:if test="${not empty building.id}">
                                    <input id="btnSave" type="button" class="btn btn-primary" value="Cập nhật"/>
                                </c:if>
                                <c:if test="${empty building.id}">
                                    <input id="btnSave" type="button" class="btn btn-primary" value="Thêm"/>
                                </c:if>
                                <input id="btnCancel" type="button" class="btn btn-warning" value="Huỷ"/>
                                <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div><!-- /.row -->
</div> <!--page-content -->
<!-- /.main-content -->
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

    $("#btnCancel").click(function () {
        let id= $('#buildingId').val();

        if ('' !== id) {
            showAlertBeforeCanceling("/admin/building-list");
        }
    });
</script>

</body>
</html>