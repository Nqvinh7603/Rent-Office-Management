<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building/list"/>
<c:url var="buildingAPI" value="/api/building"/>
<c:url var="assignmentAPI" value="/api/building/assignment-building"/>
<html>
<head>
    <title>Quản lý tòa nhà</title>
</head>
<body>
<div class="main-content">
    <form:form modelAttribute="modelSearch" action="${buildingListURL}" id="listForm" method="GET">
    <div class="main-content-inner">
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href='<c:url value="/admin/home" />'>Trang chủ</a>
                </li>
                <li class="active">Danh sách tòa nhà</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content" style="padding-bottom: 0px">
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
                    <div class="widget-box">
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>
                            <div class="widget-toolbar">
                                <a href="#" data-action="collapse">
                                    <i class="ace-icon fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="widget-body">
                            <div class="widget-main">
                                <div class="form-horizontal">
                                    <div class="form-group">
                                        <div class="col-xs-6">
                                            <label for="name">Tên sản phẩm</label>
                                            <form:input path="name" cssClass="form-control"/>
                                        </div>
                                        <div class="col-xs-6">
                                            <label for="floorArea">Diện tích sàn</label>
                                            <form:input path="floorArea" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-4">
                                            <label for="districtCode">Quận hiện có</label>
                                            <br>
                                            <form:select path="districtCode" cssClass="chosen-select">
                                                <form:option value="">--- Chọn Quận ---</form:option>
                                                <form:options items="${districts}"/>
                                            </form:select>
                                        </div>
                                        <div class="col-xs-4">
                                            <label for="ward">Phường</label>
                                            <form:input path="ward" cssClass="form-control"/>
                                        </div>
                                        <div class="col-xs-4">
                                            <label for="street">Đường</label>
                                            <form:input path="street" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-4">
                                            <label for="numberOfBasement">Số tầng hầm</label>
                                            <form:input path="numberOfBasement" cssClass="form-control"/>
                                        </div>
                                        <div class="col-xs-4">
                                            <label for="direction">Hướng</label>
                                            <form:input path="direction" cssClass="form-control"/>
                                        </div>
                                        <div class="col-xs-4">
                                            <label for="level">Hạng</label>
                                            <form:input path="level" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-3">
                                            <label for="rentAreaFrom">Diện tích từ</label>
                                            <form:input path="rentAreaFrom" cssClass="form-control"/>
                                        </div>
                                        <div class="col-xs-3">
                                            <label for="rentAreaTo">Diện tích đến</label>
                                            <form:input path="rentAreaTo" cssClass="form-control"/>
                                        </div>
                                        <div class="col-xs-3">
                                            <label for="rentPriceFrom">Giá thuê từ</label>
                                            <form:input path="rentPriceFrom" cssClass="form-control"/>
                                        </div>
                                        <div class="col-xs-3">
                                            <label for="rentPriceTo">Giá thuê đến</label>
                                            <form:input path="rentPriceTo" cssClass="form-control"/>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-4">
                                            <label for="managerName">Tên quản lý</label>
                                            <form:input path="managerName" cssClass="form-control"/>
                                        </div>
                                        <div class="col-xs-4">
                                            <label for="managerPhone">Số điện thoại</label>
                                            <form:input path="managerPhone" cssClass="form-control"/>
                                        </div>
                                        <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                                            <div class="col-xs-4">
                                                <label for="staffId">Nhân viên</label>
                                                <br>
                                                <form:select path="staffId">
                                                    <form:option value="">--- Chọn nhân viên phụ trách ---</form:option>
                                                    <form:options items="${staffmaps}"/>
                                                </form:select>
                                            </div>
                                        </security:authorize>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-12">
                                        <label>Loại toà nhà</label>
                                        <br>
                                        <c:forEach var="item" items="${buildingTypes}">
                                            <label class="checkbox-inline">
                                                <form:checkbox path="types" value="${item.key}"/>${item.value}
                                            </label>
                                        </c:forEach>
                                    </div>
                                </div>
                                <button class="btn btn-md btn-success" id="btnSearch" style="margin-top: 10px">Tìm kiếm
                                    <i class="fa fa-arrow-circle-o-right" aria-hidden="true"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </br>
        <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
            <div class="row">
                <div class="col-xs-12">
                    <div class="pull-right" style="margin-right: 20px">
                        <a class="btn btn-corner btn-bold"
                           data-toggle="tooltip"
                           title="Thêm toà nhà"
                           href='<c:url value="/admin/building/edit"/>'>
                            <span><em class="fa fa-plus-circle"></em></span>
                        </a>
                        <button id="btnDeleteBuilding" type="button" disabled class="btn btn-danger btn-bold"
                                data-toggle="tooltip" title="Xóa toà nhà">
                            <span><em class="fa fa-trash"></em></span>
                        </button>
                    </div>
                </div>
            </div>
        </security:authorize>
        <br/>
            <%--<div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive">
                            &lt;%&ndash;pagesize="${modelSearch.maxPageItems}"&ndash;%&gt;
                        <display:table name="modelSearch.listResult"
                                       cellspacing="0"
                                       cellpadding="0"
                                       requestURI="${buildingListURL}"
                                       partialList="true" sort="external"
                                       size="${modelSearch.totalItems}" defaultsort="2" defaultorder="ascending"
                                       id="buildingList" pagesize="${modelSearch.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;">
                            <display:column title="<fieldset class='form-group'>
                                                            <input type='checkbox' id='checkAll' class='check-box-element'>
                                                            </fieldset>" class="center select-cell"
                                            headerClass="center select-cell">
                                <fieldset>
                                    <input type="checkbox" name="buildingIds" value="${buildingList.id}"
                                           id="checkbox_${buildingList.id}" class="check-box-element"/>
                                </fieldset>
                            </display:column>
                            <display:column headerClass="text-left" property="createdDate" title="Ngày"/>
                            <display:column headerClass="text-left" property="name" title="Tên sản phẩm"/>
                            <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                            <display:column headerClass="text-left" property="managerName" title="Tên quản lý"/>
                            <display:column headerClass="text-left" property="managerPhone" title="Số điện thoại"/>
                            <display:column headerClass="text-left" property="floorArea" title="D.T sàn"/>
                            <display:column headerClass="text-left" property="rentAreaDescription" title="D.T trống"/>
                            <display:column headerClass="text-left" property="rentPrice" title="Giá thuê"/>
                            <display:column headerClass="text-left" property="serviceFee" title="Phí dịch vụ"/>
                            <display:column headerClass="text-left" property="brokerageFee" title="Phí MG"/>
                            <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                            <display:column headerClass="col-actions" title="Thao tác">
                                <a class="btn btn-xs btn-info"
                                   data-toggle="tooltip"
                                   title="Sửa tòa nhà"
                                   href='<c:url value='/admin/building/edit/${buildingList.id}'/>'>
                                    <span><em class="ace-icon fa fa-pencil "></em></span>
                                </a>
                                <button class="btn btn-xs" data-toggle="tooltip"
                                        title="Giao tòa nhà" id="btnBuildingAssignment" buildingId="${buildingList.id}">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                </button>
                            </display:column>
                            </security:authorize>
                        </display:table>
                    </div>
                </div>
            </div>--%>
        <div class="row">
            <div class="col-xs-12">
                <div class="table-responsive">
                    <table id="buildingList"
                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                           style="margin: 3em 0 1.5em;">
                        <thead>
                        <tr>
                            <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                            <th class="center select-cell">
                                <fieldset class="form-group"><input type="checkbox" id="checkAll"
                                                                    class="check-box-element"></fieldset>
                            </th>
                            </security:authorize>
                            <th class="text-left">Ngày</th>
                            <th class="text-left">Tên sản phẩm</th>
                            <th class="text-left">Địa chỉ</th>
                            <th class="text-left">Tên quản lý</th>
                            <th class="text-left">Số điện thoại</th>
                            <th class="text-left">D.T sàn</th>
                            <th class="text-left">D.T trống</th>
                            <th class="text-left">Giá thuê</th>
                            <th class="text-left">Phí dịch vụ</th>
                            <th class="text-left">Phí MG</th>
                            <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                            <th class="col-actions">Thao tác</th>
                            </security:authorize>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${modelSearch.listResult}">
                            <tr>
                                <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                                <td>
                                    <input type="checkbox" name="buildingIds" value="${buildingList.id}"
                                           id="checkbox_${buildingLisist.id}" class="check-box-element"/>
                                </td>
                                </security:authorize>
                                <td>${item.createdDate}</td>
                                <td>${item.name}</td>
                                <td>${item.address}</td>
                                <td>${item.managerName}</td>
                                <td>${item.managerPhone}</td>
                                <td>${item.floorArea}</td>
                                <td>${item.rentAreaDescription}</td>
                                <td>${item.rentPrice}</td>
                                <td>${item.serviceFee}</td>
                                <td>${item.brokerageFee}</td>
                                <security:authorize access="hasAnyRole('ADMIN', 'MANAGER')">
                                <td>
                                    <button class="btn btn-xs" title="Giao tòa nhà"
                                            id="btnBuildingAssignment" buildingId="${item.id}">
                                        <i class="fa fa-user" aria-hidden="true"></i>
                                    </button>
                                    <c:url var="editBuilding" value="/admin/building/edit">
                                        <c:param name="id" value="${item.id}"/>
                                    </c:url>
                                    <a class="btn btn-xs btn-info" data-toggle="tooltip"
                                       title="Cập nhật toà nhà" href='${editBuilding}'><i class="fa fa-pencil-square-o"
                                                                                          aria-hidden="true"></i>
                                    </a>
                                </td>
                                </security:authorize>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="text-center">
            <ul id="pagination" class="pagination"></ul>
        </div>
        <form:hidden path="page" id="page"/>
    </div> <!-- /.page-content -->
</div>
</form:form>
</div><!-- /.main-content -->

<!-- assignmentBuilding -->
<div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered" id="staffList">
                    <thead>
                    <tr>
                        <th>Chọn nhân viên</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignBuilding">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Đóng lại</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    $('#btnSearch').click(function (e) {
        e.preventDefault();
        $('#page').val(1);
        $('#listForm').submit();
    });

    var totalPages = ${modelSearch.totalPages};
    var currentPage = ${modelSearch.page};
    var totalItems = ${modelSearch.totalItems};
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage !== page) {
                    $('#page').val(page);
                    $('#listForm').submit();
                }
            },
            // Text labels
            first: 'Trang đầu',
            prev: 'Trang trước',
            next: 'Tiếp theo',
            last: 'Trang cuối',
        });
    });

   /* $(document).ready(function () {
        const checkboxAll = $('#checkAll');
        const buildingItemsCheckbox = $('#buildingList input[type=checkbox][name="buildingIds"]');

        buildingItemsCheckbox.change(function () {
            const numberOfChecked = $('#buildingList input[type=checkbox][name="buildingIds"]:checked').length;
            const isCheckedAll = buildingItemsCheckbox.length === numberOfChecked;
            checkboxAll.prop('checked', isCheckedAll);

            if (1 <= numberOfChecked) {
                $('#btnDeleteBuilding').attr('disabled', false);
            } else {
                $('#btnDeleteBuilding').attr('disabled', true);
            }
        });
    });*/
    $(document).ready(function () {
        const checkboxAll = $('#checkAll');
        const buildingItemsCheckbox = $('#buildingList input[type=checkbox][name="buildingIds"]');

        function updateDeleteButtonState() {
            const numberOfChecked = buildingItemsCheckbox.filter(':checked').length;
            $('#btnDeleteBuilding').attr('disabled', numberOfChecked === 0);
        }

        checkboxAll.change(function () {
            const isChecked = $(this).prop('checked');
            buildingItemsCheckbox.prop('checked', isChecked);
            updateDeleteButtonState();
        });

        buildingItemsCheckbox.change(function () {
            const numberOfChecked = buildingItemsCheckbox.filter(':checked').length;
            const isCheckedAll = buildingItemsCheckbox.length === numberOfChecked;
            checkboxAll.prop('checked', isCheckedAll);

            updateDeleteButtonState();
        });

        updateDeleteButtonState();
    });

    $(document).on("click", "#buildingList button#btnBuildingAssignment", function (e) {
        e.preventDefault();
        openModalAssignmentBuilding();
        let buildingId = $(this).attr('buildingId');
        $('#buildingId').val(buildingId);
        loadStaff(buildingId);
    })

    function openModalAssignmentBuilding() {
        $('#assignmentBuildingModal').modal();
    }

    function loadStaff(buildingId) {
        $.ajax({
            type: "GET",
            url: "${buildingAPI}/" + buildingId + "/staffs",
            dataType: "json",
            success: function (response) {
                console.log('success');
                let row = '';
                $.each(response, function (index, item) {
                    row += '<tr>';
                    row += '<td class = "text-center"><input type="checkbox" value=' + item.id + ' id ="checkbox_' + item.id + '" class = "check-box-element" ' + item.checked + '/></td>';
                    row += '<td class = "text-center">' + item.fullName + '</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
            },
            error: function (response) {
                showNotification('error', 'Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.');
            }
        });
    }

    $('#btnAssignBuilding').click(function (e) {
        e.preventDefault();
        let dataLength;
        let data = {};
        data['buildingId'] = $('#buildingId').val();
        data['staffIds'] = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        dataLength = data['staffIds'].length;
        showConfirmationAlertBeforeAction(function () {
            assignStaff(data);
        }, "Giao", "Giao ".concat(dataLength > 1 ? "các " : "", "nhân viên đã chọn quản lý tòa nhà này?"));
    });

    function assignStaff(data) {
        $.ajax({
            type: "POST",
            url: "${buildingAPI}/assignment-building",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json',
            success: function () {
                showNotification('success', 'Giao tòa nhà cho nhân viên quản lý thành công!');
            },
            error: function () {
                showNotification('error', 'Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.');
            }
        });
    }

    $('#btnDeleteBuilding').click(function (e) {
        e.preventDefault();
        let buildingIds = $('#buildingList').find(
            'tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        showConfirmationAlertBeforeAction(function () {
            deleteBuilding(buildingIds);
        }, "Xóa", "Xóa ".concat(buildingIds > 1 ? "các " : "", "tòa nhà đã chọn?"));
    });

    function deleteBuilding(data) {
        $.ajax({
            type: "DELETE",
            url: "${buildingAPI}",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: 'application/json',
            success: function () {
                window.location.href = "<c:url value ='/admin/building/list?message=delete_success'/>"
            },
            error: function () {
                showNotification('error', 'Đã xảy ra lỗi hệ thống, vui lòng thử lại sau.');
            }
        });
    }

</script>
</body>
</html>