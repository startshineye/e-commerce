 
1.实现新增
 *  页面:
   list.jsp:
      onclick="toAction("${webPath}/banner/toinsert")";
   insert.jsp
                 验证:insert_onclick()函数中实现
           toAction("${webPath}/banner/insert")
           
controller:
       保存方法中:"redirect:list"
-----------------------------------------
* 实现insert.jsp前端的验证,非空实时验证

        
   
