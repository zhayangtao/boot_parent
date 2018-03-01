#sql("findPrettyGirl")
SELECT * FROM user where age > ? and age < ? 
#end

#sql("find')
  SELECT * FROM user
  #for(x: cond)
    #(for.index == 0 ? "where" : "and") #(x.key) #para(x.value)
  #end
#end
