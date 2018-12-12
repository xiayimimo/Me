<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>仓库管理登录页面</title>
<script type="text/javascript" src="../js/myjs/manageLogin.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script>
	function validateForm(){
		//1.取出用户名和密码
		var account=document.getElementById("txtAccount").value;
		var pwd=document.getElementById("txtPwd").value;
		//2.定义验证的正则表达式
		var reg=/.{1,8}/;
		//3.使用正则对数据进行验证
		if(!reg.test(account)||!reg.test(pwd)){//验证失败
			document.getElementById("info").innerHTML="<font color='red' size='2'>数据不完整,请重试！</font>";
			return false;
		}else{//成功
			return true;
		}	
	}
</script>
</head>

<body>
	<table align="center" width="645px" border="0">
    	<tr>
        	<td height="60px"></td>
        </tr>
    	<tr>
        	<td background="../img/loginbackground.png" width="345px" height="346px">
            	<table border="0" width="100%" height="100%">
                	<tr>
                    	<td width="300px"></td>
                        <td width="345px">
  <!--                     	<form action="" method="post" onsubmit="return validateForm()">  --> 
                            	<table width="70%" height="30%" border="0">
                                	<tr><td colspan="2" height="30px" style="font-family:'楷体_GB2312';font-size:20px; font-weight:bold;">欢迎进入后台管理系统</td>
                                    </tr>
                                    <tr>
                                    	<td style="font-size:12px">用户名:</td>
                                        <td>
                                        	<input type="text" name="account" id="txtAccount"/>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<td style="font-size:12px">密码:</td>
                                        <td>
                                        	<input type="password" name="pwd" id="txtPwd"/>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<td colspan="2" align="center">
                                        	<input type="image" src="../img/b_login.gif" onclick='manageLogin(1)'/>
                                        	<input type="image" src="../img/b_clean.gif" onclick='manageLogin(0)'/>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<td colspan="2" id="info">
                                        	<font color="red" size="2">
                                        		
                                        	</font>
                                        </td>
                                    </tr>
                                </table>
<!--                          </form>  -->   
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</body>
</html>

