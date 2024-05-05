<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                <li class="active">Dashboard</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4 class="widget-title">Tìm tòa nhà</h4>
                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <form:form modelAttribute="modelSearch" action="${buildingListURL}" id="listForm"
                                       method="GET">
                                <div class="row">
                                    <div class="col-xs-12">
                                        <!-- PAGE CONTENT BEGINS -->
                                        <div class="col-sm-6">
                                            <div>
                                                <label><b>Tên tòa
                                                    nhà</b></label>
                                                    <%--<input type="text" id="name" class="form-control" name="name" value="${modelSearch.name}"/>--%>
                                                <!-- name="name" hỗ trợ việc chúng ta tìm kiếm, gửi dữ liệu từ client về server thì nó nhận ra đc  -->
                                                <form:input path="name" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-6">
                                            <div>
                                                <label><b>Diện tích
                                                    sàn</b></label>
                                                <form:input type="number" path="floorArea"
                                                            value="${modelSearch.floorArea}"
                                                            cssClass="form-control"/><br>
                                                    <%-- type="number" -> dùng kiểu bình thường --%>
                                            </div>
                                        </div>

                                        <div class="col-sm-2">
                                            <div>
                                                <label><b>Quận hiện
                                                    có</b></label>
                                                <form:select path="districtCode" cssClass="form-control">
                                                    <option selected value="">Chọn Quận</option>
                                                    <c:forEach var="item" items="${districts}">
                                                        <form:option value="${item.code}">${item.name}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                                <br>
                                            </div>
                                        </div>

                                        <div class="col-sm-5">
                                            <div>
                                                <label><b>Phường</b></label>
                                                <form:input path="ward" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-5">
                                            <div>
                                                <label><b>Đường</b></label>
                                                <form:input path="street" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label><b>Số tầng
                                                    hầm</b></label>
                                                <form:input type="number" path="numberOfBasement"
                                                            value="${modelSearch.numberOfBasement}"
                                                            cssClass="form-control"/><br>

                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label><b>Hướng</b></label>
                                                <form:input type="text" path="direction" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label><b>Hạng</b></label>
                                                <form:input type="text" path="level" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-3">
                                            <div>
                                                <label><b>Diện tích
                                                    từ</b></label>
                                                <form:input type="text" path="rentAreaFrom" cssClass="form-control"
                                                            value="${modelSearch.rentAreaFrom}"/><br>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label><b>Diện tích
                                                    đến</b></label>
                                                <form:input type="text" path="rentAreaTo" cssClass="form-control"
                                                            value="${modelSearch.rentAreaTo}"/><br>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label><b>Gía thuê
                                                    từ</b></label>
                                                <form:input type="text" path="rentPriceFrom" cssClass="form-control"
                                                            value="${modelSearch.rentPriceFrom}"/><br>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label><b>Gía thuê
                                                    đến</b></label>
                                                <form:input type="text" path="rentPriceTo" cssClass="form-control"
                                                            value="${modelSearch.rentPriceTo}"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label><b>Tên quản
                                                    lí</b></label>
                                                <form:input type="text" path="managerName" cssClass="form-control"/><br>
                                            </div>
                                        </div>

                                        <div class="col-sm-4">
                                            <div>
                                                <label><b>Điện
                                                    thoại quản lý</b></label>
                                                <form:input type="text" path="managerPhone"
                                                            cssClass="form-control"/><br>
                                            </div>
                                        </div>
                                        <div class="col-sm-3">
                                            <div>
                                                <label><b>Chọn nhân
                                                    viên quản lý</b></label>
                                                <form:select path="staffId" class="form-control">
                                                    <option selected value="">Chọn nhân viên quản lí</option>
                                                    <c:forEach var="item" items="${staffmaps}">
                                                        <form:option value="${item.id}">${item.fullName}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                                <br>
                                            </div>
                                        </div>

                                        <div class="col-sm-6 ">
                                                <c:forEach var="item" items="${buildingTypes}">
                                                    <input type="checkbox" id="rent" name="types" value="${item.code}" />
                                                    <label for="rent" style="font-weight: bold; margin-right: 10px; display: inline-block">${item.name}</label>
                                                </c:forEach>
                                        </div>


                                        <div class="col-sm-12">
                                            <br>
                                            <button type="button" id="btnSearch" class="btn btn-sm btn-success">
                                                Tìm kiếm
                                                <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                            </button>
                                        </div>
                                        <!-- PAGE CONTENT ENDS -->
                                    </div><!-- /.col -->
                                </div>
                            </form:form>
                        </div>
                    </div>

                </div><!-- /.row -->

                <div class="row">
                    <div class="col-xs-12">
                        <div class="pull-right">
                            <button type="button" class="btn btn-success" data-toggle="tooltip"
                                    title="Thêm tòa nhà" onclick="window.location.href='<c:url value="/admin/building-edit"/>'">
                                <i class="fa fa-plus-circle" aria-hidden="true"></i>
                            </button>

                            <button type="button" class="btn btn-danger " data-toggle="tooltip"
                                    title="Xóa tòa nhà" id="btnDeleteBuilding">
                                <i class="fa fa-trash" aria-hidden="true"></i>
                            </button>
                        </div>
                    </div>
                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-12">
                        <table id="buildingList" class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="checkItem" id="selectAll" class="ace"/>
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th>Tên sản phẩm</th>
                                <th>Địa chỉ</th>
                                <th>Tên quản lí</th>
                                <th>Số điện thoại</th>
                                <th>Diện tích sàn</th>
                                <th>Giá thuê</th>
                                <th>Phí dịch vụ</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach var="item" items="${buildings}">
                                <tr>
                                    <td> <input type="checkbox" value="${item.id}" name="checkBuildings[]"></td>
                                    <td>${item.name}</td>
                                    <td>${item.address}</td>
                                    <td>${item.managerName}</td>
                                    <td>${item.managerPhone}</td>
                                    <td>${item.floorArea}</td>
                                    <td>${item.rentPrice}</td>
                                    <td>${item.serviceFee}</td>
                                    <td>
                                        <button class="btn btn-xs" data-toggle="tooltip"
                                                title="Giao tòa nhà" value="${item.id}" onclick="assignmentBuilding(value)">
                                            <i class="fa fa-home" aria-hidden="true"></i>
                                        </button>
                                        <button class="btn btn-xs btn-dark" data-toggle="tooltip"
                                                title="Sửa thông tin toà nhà" value="${item.id}"
                                                onclick="editBuilding(value)">
                                            <i class="fa fa-edit" aria-hidden="true"></i>
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div><!-- /.main-content -->

<div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Giao tòa nhà cho nhân viên</h4>
            </div>

            <div class="modal-body">
                <form>
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th class="center">
                            <label class="pos-rel">
                                <input type="checkbox" id="selectAll2" class="ace"/>
                                <span class="lbl"></span>
                            </label>
                        </th>
                        <th class="text-center">Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody id="dsnv">
                </table>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="assignment">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>

<script>
    var buildingid;
    function assignmentBuilding(value) {
        buildingid = value;
        // loadStaffAssign(value); // load nhân viên quản lí building
        // function này sẽ call 1 api về từ dưới db - nó sẽ load dsach nv lên -> sử dụng ajax
        $("#dsnv").empty();
        $.ajax({
            type: "GET",
            url: "<c:url value='/api/building'/>" + '/' + value + '/staff',
            //data: JSON.stringify(data),  // data gửi về
            dataType: "json",              // kiểu dữ liệu gửi từ server
            contentType: "application/json",   // gửi từ server

            success: function (response) {
                // data : chính là cục chứa data của tk staffListDTO
                var arrBuilding  = response;
                var row = '';
                arrBuilding.forEach(function(item) {
                    var row = '<tr>'
                        +  '<td class=text-center>' +
                        '<input type="checkbox" ' + item.checked + ' name="checkStaffs[]" value="' + item.id  + '" />'
                        +  '</td>'
                        +  '<td>'+ item.fullName + '</td>' +
                        '</tr>'
                    $("#dsnv").append(row);
                });
            },
            error: function (response) {
                console.log('failed')
                console.log(response)
            }
        });
        openModalAssignmentBuilding();
    }

    /* Giao Toà Nhà */
    $("#assignment").click(function (e) {
        e.preventDefault();
        var values = [];
        var data = {};
        var checkData = $('input[name="checkStaffs[]"]:checked')

        $.each(checkData, function () {
            values.push($(this).val());
        });

        data["staffIds"] = values;

        $.ajax({
            type: "POST",
            url: '<c:url value="/api/building"/>' + '/' + buildingid + '/assignment',
            data: JSON.stringify(data),
            dataType: "json",               //kieu du lieu tu server tra ve client
            contentType: "application/json",//kieu du lieu tu client gui ve server
            success: function (response) {
                console.log("sucess");
                window.location.reload();
            },
            error: function (response) {
                alert("fail")
                console.log(response)
            }
        });
    })

    function openModalAssignmentBuilding() {
        $('#assignmentBuildingModal').modal();
    }

    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        data['buildingIds'] = buildingIds; // push buildingIds vào data
        deleteBuilding(data);
    });

    function deleteBuilding(data) {
        $.ajax({
            type: "DELETE",
            url: "http://localhost:8080/api-deleteBuilding",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                console.log('success');
            },
            error: function (response) {
                console.log('failed')
                console.log(response)
            }
        });
    }

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $("#listForm").submit();
    });
    function editBuilding(value) {
        window.location.href = '<c:url value="/admin/building-edit" />' + '?buildingid=' + value;
    }


    var idOne;
    $("#btnDeleteBuilding").click(function (e) {
        e.preventDefault();
        var values = [];
        if (idOne != null)
            values.push(idOne);
        $.each($("input[name='checkBuildings[]']:checked"), function () {
            values.push($(this).val());
        });
        var data = {};
        data["buildingId"] = values;
        $.ajax({
            type: "DELETE",
            url: '<c:url value="/api/building"/>',
            data:JSON.stringify(data),
            dataType: "json",//kieu du lieu tu server tra ve client
            contentType: "application/json",//kieu du lieu tu client gui ve server
            success: function (response) {
                window.location.reload();
            },
            error: function (response) {
                alert("fail")
                console.log(response)
            }
        });
    })
    $('#selectAll').change(function() {
        $('input[name="checkBuildings[]"]').prop('checked', this.checked);
    });

    // id : selectAll2 -> checkbox - staff
    $("#selectAll2").click(function () {
        $('input[name="checkStaffs[]"]').prop('checked', this.checked);
    })

</script>
</body>
</html>

