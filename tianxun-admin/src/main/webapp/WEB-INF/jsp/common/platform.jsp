<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/inc/taglib.inc" %>
<%@ include file="/WEB-INF/inc/script.inc" %>
<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<base href="${base}">
    <title>天寻 | Dashboard</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Toastr style -->
<!--     <link href="css/plugins/toastr/toastr.min.css" rel="stylesheet"> -->

    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">

</head>

<body>
    <div id="wrapper">
    	<!-- 左侧菜单 -->
        <jsp:include page="/WEB-INF/jsp/common/main-menu.jsp"></jsp:include>

        <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
        <!-- 头部工具栏区 -->
        <jsp:include page="/WEB-INF/jsp/common/main-header.jsp"></jsp:include>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="wrapper wrapper-content" style="margin-top:-20px;margin-left:-10px;">
                        <img alt="welcome" id="welcome" src="img/welcome.jpg">
                </div>
                <!-- 底部版权 -->
                <jsp:include page="/WEB-INF/jsp/common/main-footer.jsp"></jsp:include>
            </div>
        </div>

        </div>
<!--         <div class="small-chat-box fadeInRight animated"> -->

<!--             <div class="heading" draggable="true"> -->
<!--                 <small class="chat-date pull-right"> -->
<!--                     02.19.2015 -->
<!--                 </small> -->
<!--                 Small chat -->
<!--             </div> -->

<!--             <div class="content"> -->

<!--                 <div class="left"> -->
<!--                     <div class="author-name"> -->
<!--                         Monica Jackson <small class="chat-date"> -->
<!--                         10:02 am -->
<!--                     </small> -->
<!--                     </div> -->
<!--                     <div class="chat-message active"> -->
<!--                         Lorem Ipsum is simply dummy text input. -->
<!--                     </div> -->

<!--                 </div> -->
<!--                 <div class="right"> -->
<!--                     <div class="author-name"> -->
<!--                         Mick Smith -->
<!--                         <small class="chat-date"> -->
<!--                             11:24 am -->
<!--                         </small> -->
<!--                     </div> -->
<!--                     <div class="chat-message"> -->
<!--                         Lorem Ipsum is simpl. -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="left"> -->
<!--                     <div class="author-name"> -->
<!--                         Alice Novak -->
<!--                         <small class="chat-date"> -->
<!--                             08:45 pm -->
<!--                         </small> -->
<!--                     </div> -->
<!--                     <div class="chat-message active"> -->
<!--                         Check this stock char. -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="right"> -->
<!--                     <div class="author-name"> -->
<!--                         Anna Lamson -->
<!--                         <small class="chat-date"> -->
<!--                             11:24 am -->
<!--                         </small> -->
<!--                     </div> -->
<!--                     <div class="chat-message"> -->
<!--                         The standard chunk of Lorem Ipsum -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="left"> -->
<!--                     <div class="author-name"> -->
<!--                         Mick Lane -->
<!--                         <small class="chat-date"> -->
<!--                             08:45 pm -->
<!--                         </small> -->
<!--                     </div> -->
<!--                     <div class="chat-message active"> -->
<!--                         I belive that. Lorem Ipsum is simply dummy text. -->
<!--                     </div> -->
<!--                 </div> -->


<!--             </div> -->
<!--             <div class="form-chat"> -->
<!--                 <div class="input-group input-group-sm"><input type="text" class="form-control"> <span class="input-group-btn"> <button -->
<!--                         class="btn btn-primary" type="button">Send -->
<!--                 </button> </span></div> -->
<!--             </div> -->

<!--         </div> -->
<!--         <div id="small-chat"> -->

<!--             <span class="badge badge-warning pull-right">5</span> -->
<!--             <a class="open-small-chat"> -->
<!--                 <i class="fa fa-comments"></i> -->

<!--             </a> -->
<!--         </div> -->
        <div id="right-sidebar">
            <div class="sidebar-container">

                <ul class="nav nav-tabs navs-3">

                    <li class="active"><a data-toggle="tab" href="#tab-1">
                        Notes
                    </a></li>
                    <li><a data-toggle="tab" href="#tab-2">
                        Projects
                    </a></li>
                    <li class=""><a data-toggle="tab" href="#tab-3">
                        <i class="fa fa-gear"></i>
                    </a></li>
                </ul>

                <div class="tab-content">


                    <div id="tab-1" class="tab-pane active">

                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> Latest Notes</h3>
                            <small><i class="fa fa-tim"></i> You have 10 new message.</small>
                        </div>

                        <div>

                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a1.jpg">

                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">

                                        There are many variations of passages of Lorem Ipsum available.
                                        <br>
                                        <small class="text-muted">Today 4:21 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a2.jpg">
                                    </div>
                                    <div class="media-body">
                                        The point of using Lorem Ipsum is that it has a more-or-less normal.
                                        <br>
                                        <small class="text-muted">Yesterday 2:45 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a3.jpg">

                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">
                                        Mevolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                                        <br>
                                        <small class="text-muted">Yesterday 1:10 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a4.jpg">
                                    </div>

                                    <div class="media-body">
                                        Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the
                                        <br>
                                        <small class="text-muted">Monday 8:37 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a8.jpg">
                                    </div>
                                    <div class="media-body">

                                        All the Lorem Ipsum generators on the Internet tend to repeat.
                                        <br>
                                        <small class="text-muted">Today 4:21 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a7.jpg">
                                    </div>
                                    <div class="media-body">
                                        Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.
                                        <br>
                                        <small class="text-muted">Yesterday 2:45 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a3.jpg">

                                        <div class="m-t-xs">
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                            <i class="fa fa-star text-warning"></i>
                                        </div>
                                    </div>
                                    <div class="media-body">
                                        The standard chunk of Lorem Ipsum used since the 1500s is reproduced below.
                                        <br>
                                        <small class="text-muted">Yesterday 1:10 pm</small>
                                    </div>
                                </a>
                            </div>
                            <div class="sidebar-message">
                                <a href="#">
                                    <div class="pull-left text-center">
                                        <img alt="image" class="img-circle message-avatar" src="img/a4.jpg">
                                    </div>
                                    <div class="media-body">
                                        Uncover many web sites still in their infancy. Various versions have.
                                        <br>
                                        <small class="text-muted">Monday 8:37 pm</small>
                                    </div>
                                </a>
                            </div>
                        </div>

                    </div>

                    <div id="tab-2" class="tab-pane">

                        <div class="sidebar-title">
                            <h3> <i class="fa fa-cube"></i> Latest projects</h3>
                            <small><i class="fa fa-tim"></i> You have 14 projects. 10 not completed.</small>
                        </div>

                        <ul class="sidebar-list">
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Business valuation</h4>
                                    It is a long established fact that a reader will be distracted.

                                    <div class="small">Completion with: 22%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                    </div>
                                    <div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Contract with Company </h4>
                                    Many desktop publishing packages and web page editors.

                                    <div class="small">Completion with: 48%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 48%;" class="progress-bar"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Meeting</h4>
                                    By the readable content of a page when looking at its layout.

                                    <div class="small">Completion with: 14%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 14%;" class="progress-bar progress-bar-info"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-primary pull-right">NEW</span>
                                    <h4>The generated</h4>
                                    <!--<div class="small pull-right m-t-xs">9 hours ago</div>-->
                                    There are many variations of passages of Lorem Ipsum available.
                                    <div class="small">Completion with: 22%</div>
                                    <div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Business valuation</h4>
                                    It is a long established fact that a reader will be distracted.

                                    <div class="small">Completion with: 22%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 22%;" class="progress-bar progress-bar-warning"></div>
                                    </div>
                                    <div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Contract with Company </h4>
                                    Many desktop publishing packages and web page editors.

                                    <div class="small">Completion with: 48%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 48%;" class="progress-bar"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <div class="small pull-right m-t-xs">9 hours ago</div>
                                    <h4>Meeting</h4>
                                    By the readable content of a page when looking at its layout.

                                    <div class="small">Completion with: 14%</div>
                                    <div class="progress progress-mini">
                                        <div style="width: 14%;" class="progress-bar progress-bar-info"></div>
                                    </div>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="label label-primary pull-right">NEW</span>
                                    <h4>The generated</h4>
                                    <!--<div class="small pull-right m-t-xs">9 hours ago</div>-->
                                    There are many variations of passages of Lorem Ipsum available.
                                    <div class="small">Completion with: 22%</div>
                                    <div class="small text-muted m-t-xs">Project end: 4:00 pm - 12.06.2014</div>
                                </a>
                            </li>

                        </ul>

                    </div>

                    <div id="tab-3" class="tab-pane">

                        <div class="sidebar-title">
                            <h3><i class="fa fa-gears"></i> Settings</h3>
                            <small><i class="fa fa-tim"></i> You have 14 projects. 10 not completed.</small>
                        </div>

                        <div class="setings-item">
                    <span>
                        Show notifications
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example">
                                    <label class="onoffswitch-label" for="example">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                    <span>
                        Disable Chat
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" checked class="onoffswitch-checkbox" id="example2">
                                    <label class="onoffswitch-label" for="example2">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                    <span>
                        Enable history
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example3">
                                    <label class="onoffswitch-label" for="example3">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                    <span>
                        Show charts
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example4">
                                    <label class="onoffswitch-label" for="example4">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                    <span>
                        Offline users
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked name="collapsemenu" class="onoffswitch-checkbox" id="example5">
                                    <label class="onoffswitch-label" for="example5">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                    <span>
                        Global search
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" checked name="collapsemenu" class="onoffswitch-checkbox" id="example6">
                                    <label class="onoffswitch-label" for="example6">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="setings-item">
                    <span>
                        Update everyday
                    </span>
                            <div class="switch">
                                <div class="onoffswitch">
                                    <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="example7">
                                    <label class="onoffswitch-label" for="example7">
                                        <span class="onoffswitch-inner"></span>
                                        <span class="onoffswitch-switch"></span>
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="sidebar-content">
                            <h4>Settings</h4>
                            <div class="small">
                                I belive that. Lorem Ipsum is simply dummy text of the printing and typesetting industry.
                                And typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s.
                                Over the years, sometimes by accident, sometimes on purpose (injected humour and the like).
                            </div>
                        </div>

                    </div>
                </div>

            </div>



        </div>
    </div>

    <!-- Mainly scripts -->
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/jquery.serialize-object.min.js" type="text/javascript"></script>

    <!-- Flot -->
<!--     <script src="js/plugins/flot/jquery.flot.js"></script> -->
<!--     <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script> -->
<!--     <script src="js/plugins/flot/jquery.flot.spline.js"></script> -->
<!--     <script src="js/plugins/flot/jquery.flot.resize.js"></script> -->
<!--     <script src="js/plugins/flot/jquery.flot.pie.js"></script> -->

    <!-- Peity -->
<!--     <script src="js/plugins/peity/jquery.peity.min.js"></script> -->
<!--     <script src="js/demo/peity-demo.js"></script> -->

    <!-- Custom and plugin javascript -->
    <script src="js/inspinia.js"></script>
    <script src="js/plugins/pace/pace.min.js"></script>
    <script src="js/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>

    <!-- jQuery UI -->
<!--     <script src="js/plugins/jquery-ui/jquery-ui.min.js"></script> -->

    <!-- GITTER -->
<!--     <script src="js/plugins/gritter/jquery.gritter.min.js"></script> -->

    <!-- Sparkline -->
<!--     <script src="js/plugins/sparkline/jquery.sparkline.min.js"></script> -->

    <!-- Sparkline demo data  -->
<!--     <script src="js/demo/sparkline-demo.js"></script> -->

    <!-- ChartJS-->
<!--     <script src="js/plugins/chartJs/Chart.min.js"></script> -->

    <!-- Toastr -->
<!--     <script src="js/plugins/toastr/toastr.min.js"></script> -->


    <script>
        $(document).ready(function() {
//             setTimeout(function() {
//                 toastr.options = {
//                     closeButton: true,
//                     progressBar: true,
//                     showMethod: 'slideDown',
//                     timeOut: 4000
//                 };
//                 toastr.success('天寻！', '欢迎来到天寻！');

//             }, 1300);


//             var data1 = [
//                 [0,4],[1,8],[2,5],[3,10],[4,4],[5,16],[6,5],[7,11],[8,6],[9,11],[10,30],[11,10],[12,13],[13,4],[14,3],[15,3],[16,6]
//             ];
//             var data2 = [
//                 [0,1],[1,0],[2,2],[3,0],[4,1],[5,3],[6,1],[7,5],[8,2],[9,3],[10,2],[11,1],[12,0],[13,2],[14,8],[15,0],[16,0]
//             ];
//             $("#flot-dashboard-chart").length && $.plot($("#flot-dashboard-chart"), [
//                 data1, data2
//             ],
//                     {
//                         series: {
//                             lines: {
//                                 show: false,
//                                 fill: true
//                             },
//                             splines: {
//                                 show: true,
//                                 tension: 0.4,
//                                 lineWidth: 1,
//                                 fill: 0.4
//                             },
//                             points: {
//                                 radius: 0,
//                                 show: true
//                             },
//                             shadowSize: 2
//                         },
//                         grid: {
//                             hoverable: true,
//                             clickable: true,
//                             tickColor: "#d5d5d5",
//                             borderWidth: 1,
//                             color: '#d5d5d5'
//                         },
//                         colors: ["#1ab394", "#464f88"],
//                         xaxis:{
//                         },
//                         yaxis: {
//                             ticks: 4
//                         },
//                         tooltip: false
//                     }
//             );

//             var doughnutData = [
//                 {
//                     value: 300,
//                     color: "#a3e1d4",
//                     highlight: "#1ab394",
//                     label: "App"
//                 },
//                 {
//                     value: 50,
//                     color: "#dedede",
//                     highlight: "#1ab394",
//                     label: "Software"
//                 },
//                 {
//                     value: 100,
//                     color: "#b5b8cf",
//                     highlight: "#1ab394",
//                     label: "Laptop"
//                 }
//             ];

//             var doughnutOptions = {
//                 segmentShowStroke: true,
//                 segmentStrokeColor: "#fff",
//                 segmentStrokeWidth: 2,
//                 percentageInnerCutout: 45, // This is 0 for Pie charts
//                 animationSteps: 100,
//                 animationEasing: "easeOutBounce",
//                 animateRotate: true,
//                 animateScale: false
//             };

//             var ctx = document.getElementById("doughnutChart").getContext("2d");
//             var DoughnutChart = new Chart(ctx).Doughnut(doughnutData, doughnutOptions);

//             var polarData = [
//                 {
//                     value: 300,
//                     color: "#a3e1d4",
//                     highlight: "#1ab394",
//                     label: "App"
//                 },
//                 {
//                     value: 140,
//                     color: "#dedede",
//                     highlight: "#1ab394",
//                     label: "Software"
//                 },
//                 {
//                     value: 200,
//                     color: "#b5b8cf",
//                     highlight: "#1ab394",
//                     label: "Laptop"
//                 }
//             ];

//             var polarOptions = {
//                 scaleShowLabelBackdrop: true,
//                 scaleBackdropColor: "rgba(255,255,255,0.75)",
//                 scaleBeginAtZero: true,
//                 scaleBackdropPaddingY: 1,
//                 scaleBackdropPaddingX: 1,
//                 scaleShowLine: true,
//                 segmentShowStroke: true,
//                 segmentStrokeColor: "#fff",
//                 segmentStrokeWidth: 2,
//                 animationSteps: 100,
//                 animationEasing: "easeOutBounce",
//                 animateRotate: true,
//                 animateScale: false
//             };
//             var ctx = document.getElementById("polarChart").getContext("2d");
//             var Polarchart = new Chart(ctx).PolarArea(polarData, polarOptions);

        });
        
        $(window).resize(function(){
        	var win_width = $(window).width();
            var win_height = $(window).height();
            $("#welcome").attr({width:win_width,height:win_height});
        });
    </script>
</body>
</html>