<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingEditURL" value="/api/building"/>
<html>
<head>
    <title>Chỉnh sửa tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>
                <li class="active">Building Edit</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form:form modelAttribute="modelBuildingEdit" cssClass="form-horizontal" id="formEdit" method="get">
                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right" for="name"> Tên tòa nhà </label>
                            <div class="col-sm-9">
                                <form:input path="name" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Quận </label>
                            <div class="col-sm-9">
                                <select class="form-control" name="district">
                                    <option>Chọn quận</option>
                                    <c:forEach items="${modelBuildingEdit.districts}" var="item">
                                        <option ${item.selected} value="${item.code}">${item.name}</option>
                                    </c:forEach>
                                </select>
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
                                <input type="number" class="form-control" name="numberOfBasement"
                                       value="${modelBuildingEdit.numberOfBasement}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Diện tích sàn </label>
                            <div class="col-sm-9">
                                <input type="number" class="form-control" name="floorArea"
                                       value="${modelBuildingEdit.floorArea}"/>
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
                                <form:input path="rentPriceDescription" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Gía thuê </label>
                            <div class="col-sm-9">
                                <form:input path="rentPrice" cssClass="form-control"/>
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
                            <label class="col-sm-2 control-label no-padding-right"> Loại tòa nhà </label>
                            <div class="col-sm-9">
                                <c:forEach items="${modelBuildingEdit.buildingTypes}" var="item">
                                    <div class="form-check" style="display:inline-block; margin-right: 20px; vertical-align: bottom;">
                                        <form:checkbox path="types" value="${item.code}" cssClass="form-check-input"/>
                                        <label class="form-check-label">${item.name}</label>
                                    </div>
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
                            <label class="col-sm-2 control-label no-padding-right"> Tên quản lí </label>
                            <div class="col-sm-9">
                                <form:input path="managerName" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label no-padding-right"> Số điện thoại quản lý </label>
                            <div class="col-sm-9">
                                <input type="number" class="form-control" name="managerPhone"
                                       value="${modelBuildingEdit.managerPhone}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <c:if test="${modelBuildingEdit.id == null}">
                                    <button type="button" class="btn btn-primary" id="btnEditBuilding" name="">Thêm tòa nhà
                                    </button>
                                </c:if>
                                <c:if test="${modelBuildingEdit.id != null}" >
                                    <button type="button" class="btn btn-primary" id="btnEditBuilding">Cập nhật tòa nhà</button>
                                </c:if>
                                <button type="button" class="btn btn-danger" id="close" name="">Close</button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div><!-- /.row -->
        </div> <!--page-content -->
    </div>
</div><!-- /.main-content -->


<script>
    $('#btnEditBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        //var buildingTypes = [];
        var formData = $('#formEdit').serializeArray();
        var id = ${modelBuildingEdit.id} + '';
        if(id != '') {
            data["id"] = id;
        }
        var types = [];

        formData.forEach(function(item) {
            if (item.name == "types") {
                types.push(item.value);
            } else {
                data[item.name] = item.value;
            }
        })
        data["types"] = types;
        $.ajax({
            type: "PUT",
            url: '<c:url value="/api/building"/>',
            data: JSON.stringify(data),
            dataType: "json",               // kiểu dữ liệu server gửi cho client
            contentType: "application/json",//kieu du lieu tu client gui ve server
            success: function (response) {
                window.location.href = '<c:url value="/admin/building-list" />'
            },
            error: function (response) {
                alert("error : failed")
                console.log(response)
            }
        });
    })
    $("#close").click(function () {
        window.location.href = '<c:url value="/admin/building-list" />' // thay đổi URL sang trang bạn muốn chuyển đến
        window.close(); // đóng trang hiện tại
    });
</script>

</body>
</html>