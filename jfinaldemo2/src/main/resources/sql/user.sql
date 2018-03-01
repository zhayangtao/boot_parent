#sql("findUserById")
SELECT * FROM USER WHERE id=?;
#end

#sql("findUserByIdWithPara")
SELECT * FROM USER WHERE id= #para(0);
#end

#sql("findUserByIdWithPara2")
SELECT * FROM USER WHERE id= #para(id);
#end

#sql("findUserByName")
SELECT * FROM USER where username like concat('%', #para(username), '%');
#end


  #sql("findNFUserByName")
    SELECT * FROM USER where username like concat('%', #para(username), '%');
  #end
