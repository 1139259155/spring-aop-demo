# spring-aop-demo
spring-aop-demo


# Spring AOP 从入门到精通

官方介绍：https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#aop

中文版：

https://www.docs4dev.com/docs/zh/spring-framework/5.1.3.RELEASE/reference

https://www.docs4dev.com/docs/zh/spring-framework/5.1.3.RELEASE/reference/core.html#aop

## spring aop 介绍

熟记背诵：介绍		

![image-20200323221136641](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200323221136641.png)


自己理解：aop  公共功能，比如建立数据库连接，拦截器等

解耦 业务代码和 横切问题分离

![image-20200323221839204](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200323221839204.png)





## 基本使用

### 入门demo

![image-20200324001027408](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200324001027408.png)



![image-20200324000853394](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200324000853394.png)





![image-20200324000353740](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200324000353740.png)





![image-20200329225546710](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200329225546710.png)

![image-20200329230358974](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200329230358974.png)

## 术语

### AOP 概念

让我们首先定义一些中心 AOP 概念和术语。这些条款不是 Spring-specific。不幸的是，AOP 术语不是特别直观。但是，如果 Spring 使用自己的术语，那将更加令人困惑。

- Aspect：跨越多个 classes 的关注点的模块化。 Transaction management 是企业 Java applications 中横切关注点的一个很好的例子。在 Spring AOP 中，方面是使用常规 classes([schema-based 接近](https://www.docs4dev.com/docs/zh/spring-framework/5.1.3.RELEASE/reference/core.html#aop-schema))或使用`@Aspect` annotation([@AspectJ 风格](https://www.docs4dev.com/docs/zh/spring-framework/5.1.3.RELEASE/reference/core.html#aop-ataspectj))注释的常规 classes 实现的。
- 连接点：程序执行期间的一个点，例如方法的执行或 exception 的处理。在 Spring AOP 中，连接点始终表示方法执行。
- 建议：aspect 在特定连接点采取的操作。不同类型的建议包括“周围”，“之前”和“之后”建议。 (建议类型被讨论 later.)许多 AOP 框架，包括 Spring，model 作为拦截器的建议，并在连接点周围维护一系列拦截器。
- 切入点：匹配连接点的谓词。建议与切入点表达式相关联，并在切入点匹配的任何连接点处运行(对于 example，执行具有特定 name 的方法)。由切入点表达式匹配的连接点的概念是 AOP 的核心，而 Spring 默认使用 AspectJ 切入点表达式语言。
- 简介：代表类型声明其他方法或字段。 Spring AOP 允许您向任何建议的 object 引入新接口(以及相应的 implementation)。对于 example，您可以使用简介使 bean 实现`IsModified`接口，以简化缓存。 (引言在 AspectJ 中被称为 inter-type 声明 community.)
- 目标 object：由一个或多个方面建议的 object。也称为“建议对象”。由于 Spring AOP 是使用运行时代理实现的，因此 object 始终是代理 object。
- AOP 代理：由 order 中的 AOP framework 创建的 object，用于实现 aspect contracts(建议方法执行等)。在 Spring Framework 中，AOP 代理是 JDK 动态代理或 CGLIB 代理。
- 编织：将方面与其他 application 类型或 objects 链接以创建一个建议的 object。这可以在 compile time(使用 AspectJ 编译器，example)，load time 或运行时完成。与其他纯 Java AOP 框架一样，Spring AOP 在运行时执行编织。



## where to 执行 

### 1.定义切面  

--定义某个类，加上@Aspect 注解，即切面（某个class），将要织入的代码归档放到一个类中。

### 2.定义切点   

--切面类中的XXX方法  即切点

切面类中的XXX方法   --即切点，以及在哪执行该切点方法（通过表达式匹配），@Pointcut

中 前或后执行

### 3.切点——表达式匹配

**只用掌握注解形式的匹配。即所有spring bean实例中有某个注解即执行织入的代码方法**

- **任何连接点(仅在 Spring AOP 中执行方法)，其中执行方法具有`@Transactional` annotation：**

```java
@annotation(org.springframework.transaction.annotation.Transactional)
```

> '@annotation'也可以用于 binding 形式： - 请参阅以下有关如何在建议体中提供 annotation object 的建议



![image-20200324003339545](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200324003339545.png)

##### 注意 匹配切点表达式很耗性能

![image-20200324003552657](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200324003552657.png)

## when to 执行

**Advice 即 什么时刻执行 织入的（方法）代码,只用掌握Around Advice。**

Spring AOP 包括以下类型的建议：

- 建议之前：在连接点之前运行但无法阻止执行流程进入连接点的建议(除非它抛出 exception)。
- 返回建议后：在连接点正常完成后建议为 run(对于 example，如果方法返回而不抛出 exception)。
- 抛出建议后：如果方法通过抛出 exception 退出，则执行建议。
- 在(最终)建议之后：无论连接点退出的方式(正常或异常 return)，都要执行建议。
- 围绕建议：围绕连接点的建议，例如方法调用。这是最有力的建议。 around 通知可以在方法调用之前和之后执行自定义行为。它还负责选择是继续加入点还是通过返回自己的 return value 或抛出 exception 来快速建议的方法执行。

![image-20200323235351548](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200323235351548.png)







## spring aop 底层实现原理

![image-20200323221403191](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200323221403191.png)





### jdk dynamic proxy、cglib、aspectJ



![image-20200323221429300](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200323221429300.png)



### 代理机制选择

Spring AOP 使用 JDK 动态代理或 CGLIB 为给定目标 object 创建代理。 (只要有选择，JDK 动态代理就是首选)。

如果要代理的目标 object 实现至少一个接口，则使用 JDK 动态代理。目标类型实现的所有接口都是代理的。如果目标 object 未实现任何接口，则会创建 CGLIB 代理。

如果要强制使用 CGLIB 代理(对于 example，代理为目标 object 定义的每个方法，而不仅仅是那些由其接口实现的方法)，您可以这样做。但是，您应该考虑以下问题：

- 无法建议`final`方法，因为它们无法被覆盖。
- 从 Spring 3.2 开始，不再需要将 CGLIB 添加到项目 classpath 中，因为 CGLIB classes 在`org.springframework`下重新打包并直接包含在 spring-core JAR 中。这意味着 CGLIB-based 代理支持“正常工作”，就像 JDK 动态代理一样。
- 从 Spring 4.0 开始，代理 object 的构造函数不再被调用两次，因为 CGLIB 代理实例是通过 Objenesis 创建的。只有当您的 JVM 不允许构造函数绕过时，您才可以从 Spring 的 AOP 支持中看到 double 调用和相应的 debug log 条目。

要强制使用 CGLIB 代理，请将``元素的`proxy-target-class`属性的 value 设置为 true，如下所示：

```xml
<aop:config proxy-target-class="true">
    <!-- other beans defined here... -->
</aop:config>
```

要在使用 @AspectJ auto-proxy 支持时强制 CGLIB 代理，请将``元素的`proxy-target-class`属性设置为`true`，如下所示：

```xml
<aop:aspectj-autoproxy proxy-target-class="true"/>
```

> 多个``部分在运行时折叠为单个统一的 auto-proxy 创建者，这将应用指定的任何``部分(通常来自不同的 XML bean definition files)的最强代理设置。这也适用于``和``元素。

需要明确的是，在``，``或``元素上使用`proxy-target-class="true"`会强制对所有这三个元素使用 CGLIB 代理。





## 强制使用CGLib

如果您需要代理 class 而不是一个或多个接口，该怎么办？

想象一下，在我们之前的例子中，没有`Person`接口。我们需要建议一个没有实现任何业务接口的 class `Person`。在这种情况下，您可以将 Spring 配置为使用 CGLIB 代理而不是动态代理。为此，请将前面显示的`ProxyFactoryBean`上的`proxyTargetClass` property 设置为`true`。虽然最好是编程到接口而不是 classes，但在使用 legacy code 时，建议不实现接口的 classes 的功能非常有用。 (一般来说，Spring 不是规定性的.虽然它可以很容易地应用好的做法，但它避免强迫特定的 approach.)

如果您愿意，即使您有接口，也可以在任何情况下强制使用 CGLIB。

CGLIB 代理通过在运行时生成目标 class 的子类来工作。 Spring 将此生成的子类配置为将方法 calls 委托给原始目标。子类用于实现 Decorator pattern，在通知中编织。

CGLIB 代理通常应对用户透明。但是，有一些问题需要考虑：

- 无法建议`Final`方法，因为它们无法被覆盖。
- 无需将 CGLIB 添加到 classpath。从 Spring 3.2 开始，CGLIB 被重新打包并包含在 spring-core JAR 中。换句话说，CGLIB-based AOP“开箱即用”，JDK 动态代理也是如此。

CGLIB 代理和动态代理之间几乎没有性能差异。从 Spring 1.0 开始，动态代理略快一些。但是，这可能会在未来发生变化。 在这种情况下，绩效不应该是一个决定性的考虑因素。



### CGLib原理

CGLIB 代理通过在运行时生成目标 class 的子类来工作。 Spring 将此生成的子类配置为将方法 calls 委托给原始目标。子类用于实现 Decorator pattern，在通知中编织。

cglib生成的类可以 写流导出class



## good example

### 重新尝试策略

![image-20200323233739109](C:\Users\Administrator\AppData\Roaming\Typora\typora-user-images\image-20200323233739109.png)