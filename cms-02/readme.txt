-----------------------------------------------
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
2.日期属性编辑器
 
-------------------------------------------
3.调现成客户端验证
   实时验证:
   非空验证:
   顺序只能输入数字:
  实现insert.jsp前端的验证,非空实时验证
-------------------------------------------
        
   
