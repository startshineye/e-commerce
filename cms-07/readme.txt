version 07
     删除：删除多条记录,
    1.Dao：dao类的delete方法；
     delete(String ids){
     String sql = “delete from cms_banner where ”+Util.ArrayToIn;
    }
    2.前端：类似于新增上增加一个“删除”按钮。
    <input type="button" class="bnt" value=“删除" onclick="to_delete(getIds())" />
    
   验证未通过还要补充下拉列表：
      现象：当我们点击新增时候，验证不通过时候，会出现，下拉列表中终端类型为空情况。
     解决：在验证失败提示错误后hasError，重新把，后台数据拿到前台
  map.put("typeMap",dict.getDictMap(10,false));//通过工具类获取终端类型map;
  
  

  
   
