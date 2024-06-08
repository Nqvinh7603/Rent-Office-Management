<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:write property="title"/></title>

	<link rel="stylesheet" href="<c:url value="/template/admin/assets/css/bootstrap.min.css" />" />
	<link rel="stylesheet" href="<c:url value="/template/admin/font-awesome/4.5.0/css/font-awesome.min.css" />" />
    <link rel="stylesheet" href="<c:url value="/template/admin/assets/css/ace.min.css"/>" class="ace-main-stylesheet" id="main-ace-style" />
    <script type='text/javascript' src="<c:url value="/template/admin/assets/js/ace-extra.min.js" />"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

	<!-- jquery -->
	<script type='text/javascript' src="<c:url value="/template/admin/js/2.1.4/jquery.min.js"/>"></script>

	<%--sweetalert--%>
	<script type='text/javascript' src="<c:url value="/template/admin/assets/sweetalert2/sweetalert2.min.js"/>"></script>
	<link rel="stylesheet" href="<c:url value="/template/admin/assets/sweetalert2/sweetalert2.min.css"/>">

	<%--Pagination--%>
	<script src="<c:url value="/template/admin/js/paging/jquery.twbsPagination.js"/>"></script>
	<link  href="<c:url value="/template/admin/css/pagination.css"/>" rel="stylesheet" type="text/css" />

</head>
<body class="no-skin">
	<!-- header -->
    <%@ include file="/common/admin/header.jsp" %>
	<meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">
    <!-- header -->
	
	<div class="main-container" id="main-container">
		<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
		</script>
		<!-- header -->
    	<%@ include file="/common/admin/menu.jsp" %>
    	<!-- header -->

		<sitemesh:write property="body"/>
		
		<!-- footer -->
    	<%@ include file="/common/admin/footer.jsp" %>
    	<!-- footer -->
    	
    	<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse display">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>

	<%--jQuery Validation Plugin--%>
	<script src="<c:url value="/template/admin/js/jqueryvalidate/jquery.validate.min.js"/>"></script>

	<%--common javascript file--%>
	<script type="text/javascript" src="<c:url value="/template/admin/js/global_admin_script.js"/>"></script>

	<script src="<c:url value="/template/admin/assets/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/template/admin/assets/js/jquery-ui.custom.min.js"/>"></script>
	<script src="<c:url value="/template/admin/assets/js/jquery.ui.touch-punch.min.js"/>"></script>
	<script src="<c:url value="/template/admin/assets/js/jquery.easypiechart.min.js"/>"></script>
	<script src="<c:url value="/template/admin/assets/js/jquery.sparkline.min.js"/>"></script>
	<script src="<c:url value="/template/admin/assets/js/jquery.flot.min.js"/>"></script>
	<script src="<c:url value="/template/admin/assets/js/jquery.flot.pie.min.js"/>"></script>
	<script src="<c:url value="/template/admin/assets/js/jquery.flot.resize.min.js"/>"></script>
	<script src="<c:url value="/template/admin/assets/js/ace-elements.min.js"/>"></script>
	<script src="<c:url value="/template/admin/assets/js/ace.min.js"/>"></script>

	<!-- page specific plugin scripts -->
	<script src="<c:url value="/template/admin/assets/js/jquery-ui.min.js"/>"></script>

	<script type="text/javascript">
        function showAlertBeforeDelete(callback) {
            swal({
                title: "Xác nhận xóa",
                text: "Bạn có chắc chắn xóa những dòng đã chọn",
                type: "warning",
                showCancelButton: true,
                confirmButtonText: "Xác nhận",
                cancelButtonText: "Hủy bỏ",
                confirmButtonClass: "btn btn-success",
                cancelButtonClass: "btn btn-danger"
            }).then(function (res) {
                if(res.value){
                    callback();
                }else if(res.dismiss == 'cancel'){
                    console.log('cancel');
                }
            });
        }
		function showMessageConfirmation(callback, title, text) {
			swal({
				title: "Xác nhận " + title,
				text: "Bạn có chắc chắn muốn " + text,
				type: "warning",
				showConfirmButton: true,
				showCancelButton: true,
				confirmButtonText: "Xác nhận",
				cancelButtonText: "Hủy bỏ",
				confirmButtonClass: "btn btn-success",
				cancelButtonClass: "btn btn-danger"
			}).then(function (res) {
				if (res.value) {
					callback();
				}
			});
		}

		function showConfirmationAlertBeforeAction(callback, title, text) {
			swal({
				title: "Xác nhận " + title,
				text: "Bạn có chắc chắn muốn " + text,
				type: "warning",
				showConfirmButton: true,
				showCancelButton: true,
				confirmButtonText: "Xác nhận",
				cancelButtonText: "Hủy bỏ",
				confirmButtonClass: "btn btn-success",
				cancelButtonClass: "btn btn-danger"
			}).then(function (res) {
				if (res.value) {
					callback();
				}
			});
		}

		function showAlertBeforeCanceling(redirectUrl) {
			swal({
				title: "Xác nhận hủy",
				text: "Bạn có chắc chắn muốn hủy chỉnh sửa?",
				type: "warning",
				showConfirmButton: true,
				showCancelButton: true,
				confirmButtonText: "Xác nhận",
				cancelButtonText: "Hủy bỏ",
				confirmButtonClass: "btn btn-success",
				cancelButtonClass: "btn btn-danger"
			}).then(function (res) {
				if (res.value){
					if ("" !== redirectUrl) {
						window.location.href = redirectUrl;
					}
				}
			});
		}

		function showNotification(status, message) {
			swal({
				type: status,
				title: status === 'success' ? 'Thành công' : 'Thất bại',
				text: message,
			});
		}
	</script>
</body>
</html>