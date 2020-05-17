# validated_group
分组校验

实际项目中，经常使用@Valid进行参数验证，使用@Validated进行参数分组校验。因为公司项目也是使用该种方式进行校验，但是基于AOP实现，该方式缺点是对于控制器Controller必须带参数BindingResult br。为了控制方法更简便，不带参数BindingResult也能验证。于是另辟蹊径，使用全局异常方案解决这一情况。

一、AOP下带参数BindingResult实现

	AOP具体实现，此处不做讲述，以下是控制器Controller相关的参考案列(TestGroup类)。

    @PostMapping("/user")
    public Object addUser( @RequestBody User user, BindingResult br){
        Validator validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
        Set<ConstraintViolation<User>> set = validator.validate(user, User.Default.class);

		//该部分可以放到service,当有某个属性不满足时抛出异常,并给前端相应的提示信息
        for (ConstraintViolation<User> constraintViolation : set) {
           String messageTemplate = constraintViolation.getMessageTemplate();//验证信息
           System.out.println(messageTemplate);
       }

        if(br.hasErrors()){
            System.out.println(br);
        } else {
        }
        return "1";
    }

    @PutMapping("/user")
    public Object updateUser(@Validated(value = {User.Update.class, User.Default.class}) @RequestBody User user,
                        BindingResult br) {

        if(br.hasErrors()){
            System.out.println(br);
        } else {
        }
        return "2";
    }

效果图
    ![image](https://github.com/13162576590/validated_group/blob/master/post请求接口.png?raw=true)

二、全局异常实现

全局异常实现参考代码如下:

1.控制器代码

此处代码和一相比，控制器不必带参数BindingResult(TestGroup类)。

    @PostMapping("/test")
    public Result test(@RequestBody @Valid User user){
        System.out.println("=====");
        System.out.println(JSON.toJSONString(user));

        return Result.success(user);
    }

    @PutMapping("/test")
    public Result testUser(@Validated(value = {User.Update.class, User.Default.class}) @RequestBody User user) {
        System.out.println(JSON.toJSONString(user));
        return Result.success(user);
    }

2.全局异常类GlobalExceptionHandler

核心代码如下:

    /**
     * 如果抛出的是参数异常，将其当做业务异常处理
     * 参数校验不通过时，抛出以下异常
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    Result handlerMethodArgumentNotValid(HttpServletRequest request, MethodArgumentNotValidException re) {
        StringBuilder sb = new StringBuilder();

        re.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            sb.append("[").append(fieldName).append("]").append(error.getDefaultMessage()).append(";    ");
        });
        String str = StrUtil.format("参数未通过校验:{}", sb.toString());
        return Result.errorByParamsVerifyFail(str);
    }

效果图
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200517005818906.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzIxNDgwMzI5,size_16,color_FFFFFF,t_70)




