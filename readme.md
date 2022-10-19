# No transaction context
```ab -c 100 -n 2000  http://localhost:8080/nontransactional```

Runs without any issues, data stored in DB

# Transaction context
```ab -c 100 -n 2000  http://localhost:8080/transactional```

fails after a while...

```
org.springframework.transaction.CannotCreateTransactionException: Could not open R2DBC Connection for transaction; nested exception is io.r2dbc.spi.R2dbcTimeoutException: Connection acquisition timed out after 3000ms
	at org.springframework.r2dbc.connection.R2dbcTransactionManager.lambda$null$5(R2dbcTransactionManager.java:227) ~[spring-r2dbc-5.3.23.jar:5.3.23]
	Suppressed: reactor.core.publisher.FluxOnAssembly$OnAssemblyException: 
Error has been observed at the following site(s):
	*__checkpoint ⇢ Handler acquiry.AcquiryApplication$SomeController#transactional() [DispatcherHandler]
	*__checkpoint ⇢ HTTP GET "/transactional" [ExceptionHandlingWebHandler]
Original Stack Trace:
		at org.springframework.r2dbc.connection.R2dbcTransactionManager.lambda$null$5(R2dbcTransactionManager.java:227) ~[spring-r2dbc-5.3.23.jar:5.3.23]
		at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onError(FluxOnErrorResume.java:94) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.MonoFlatMap$FlatMapMain.onError(MonoFlatMap.java:172) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.FluxPeek$PeekSubscriber.onError(FluxPeek.java:222) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.FluxRetry$RetrySubscriber.onError(FluxRetry.java:95) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onError(FluxOnErrorResume.java:106) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.Operators.error(Operators.java:198) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.MonoError.subscribe(MonoError.java:53) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.Mono.subscribe(Mono.java:4455) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onError(FluxOnErrorResume.java:103) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.FluxContextWrite$ContextWriteSubscriber.onError(FluxContextWrite.java:121) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.SerializedSubscriber.onError(SerializedSubscriber.java:124) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.SerializedSubscriber.onError(SerializedSubscriber.java:124) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.FluxTimeout$TimeoutMainSubscriber.handleTimeout(FluxTimeout.java:295) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.FluxTimeout$TimeoutMainSubscriber.doTimeout(FluxTimeout.java:280) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.FluxTimeout$TimeoutTimeoutSubscriber.onNext(FluxTimeout.java:419) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.FluxOnErrorReturn$ReturnSubscriber.onNext(FluxOnErrorReturn.java:162) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.MonoDelay$MonoDelayRunnable.propagateDelay(MonoDelay.java:271) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.publisher.MonoDelay$MonoDelayRunnable.run(MonoDelay.java:286) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.scheduler.SchedulerTask.call(SchedulerTask.java:68) ~[reactor-core-3.4.23.jar:3.4.23]
		at reactor.core.scheduler.SchedulerTask.call(SchedulerTask.java:28) ~[reactor-core-3.4.23.jar:3.4.23]
		at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
		at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304) ~[na:na]
		at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136) ~[na:na]
		at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635) ~[na:na]
		at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]
Caused by: io.r2dbc.spi.R2dbcTimeoutException: Connection acquisition timed out after 3000ms
	at io.r2dbc.pool.ConnectionPool.lambda$null$11(ConnectionPool.java:168) ~[r2dbc-pool-0.9.2.RELEASE.jar:0.9.2.RELEASE]
	at reactor.core.publisher.Mono.lambda$onErrorMap$30(Mono.java:3762) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.publisher.Mono.lambda$onErrorResume$32(Mono.java:3852) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onError(FluxOnErrorResume.java:94) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.publisher.FluxContextWrite$ContextWriteSubscriber.onError(FluxContextWrite.java:121) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.publisher.SerializedSubscriber.onError(SerializedSubscriber.java:124) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.publisher.SerializedSubscriber.onError(SerializedSubscriber.java:124) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.publisher.FluxTimeout$TimeoutMainSubscriber.handleTimeout(FluxTimeout.java:295) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.publisher.FluxTimeout$TimeoutMainSubscriber.doTimeout(FluxTimeout.java:280) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.publisher.FluxTimeout$TimeoutTimeoutSubscriber.onNext(FluxTimeout.java:419) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.publisher.FluxOnErrorReturn$ReturnSubscriber.onNext(FluxOnErrorReturn.java:162) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.publisher.MonoDelay$MonoDelayRunnable.propagateDelay(MonoDelay.java:271) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.publisher.MonoDelay$MonoDelayRunnable.run(MonoDelay.java:286) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.scheduler.SchedulerTask.call(SchedulerTask.java:68) ~[reactor-core-3.4.23.jar:3.4.23]
	at reactor.core.scheduler.SchedulerTask.call(SchedulerTask.java:28) ~[reactor-core-3.4.23.jar:3.4.23]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:833) ~[na:na]
Caused by: java.util.concurrent.TimeoutException: Did not observe any item or terminal signal within 3000ms in 'Connection acquisition from [org.springframework.boot.r2dbc.OptionsCapableConnectionFactory@6ffa56fa]' (and no fallback has been configured)
	... 13 common frames omitted


```

Same happens:
- if we don't perform any database operation, just @Transactional annotated method invoked within reactive flow cause R2dbcTransactionManager to fail
- on other database engines (PostgreSql verified)

Similar issue within micronaut: https://github.com/micronaut-projects/micronaut-data/issues/1019

Spring framework issue: 
https://github.com/spring-projects/spring-framework/issues/29355