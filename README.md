# CoinPaprikaTest
Bitcoin tech test project app 
- OVERVIEW
1. Download	and	display	the	list	of	coins	from	this	API	endpoint:
   - Get	Coins:	https://api.coinpaprika.com/v1/coins
   - Get	Coin	By	Id:	https://api.coinpaprika.com/v1/coins/{id}	
- FUNCTIONALITY	
1. When	the	app	is	started,	load	and	display	a	list of	coins	
1. Order	entries	by	name
2. Filter	the	list	based	on	tags.
3. Display	a	divider	between	each	entry	
2. Display	any	extra	coin	info in	some	kind	of	popup/fragment	when	coin	entry	is	clicked	
based	on	the	second	API	request,	with	said	id as	the	query.
3. Provide	some	kind	of	refresh	option	that	reloads	the	list	
4. Display	an	error	message	if	the	list	cannot	be	loaded	(e.g.	no	network)	
5. (Extra	credit)	Animate	list	items	(hint	–	Jetpack	Compose	allows	this	easily
- DELIVERABLES
1. Write	the	app	using	Kotlin	only.
2. Use	Jetpack	Compose	for	UI.
3. Use	MVVM	design	pattern,	combined	with	RxJava	or	Coroutines	(remember;	keep	it	
simple!)
4. Write	unit	tests	against	the	ViewModel	(and	anywhere	you	feel	necessary,	using	i.e.	
Mockito),	so	it’s	tested	that	i.e.	entries	are	ordered	by	name,	etc.
5. Try	to	avoid	using	any	3rd	party	libraries
6. Deliver	your	final	project	via	Git.	We’d	like	to	see	your	process	so	please	leave	in	your	
commit	history


---------
- PROJECT EXPLANATION:
1. The project initially makes two requests to the given APIs to get all the information from the bitcoins searched by Id.
2. The repository filters the list by tags and sorted by name.
3. A double request needs to be made to get the extra information for the bitcoin.
4. API restriction in the number of request for the free version, filter applied to take only the first 10 bitcoins (this can be changed at any time, but we have a limitation there for the free version).
5. The repository is also responsible to add any new bitcoins to the database (Room database applied for easiness and convenience, reusing the same list later on for faster loading).
6. The repository gets the information for the coin selected by id from the database.
7. MVVM applied with coroutines to propagate states(loading, data an error messages) to the composable view.
8. The UI is built in Jetpack compose for the list representation and easiness of features added like swipe to refresh and animation of lists.
9. Elements used in jetpack compose: Lazy column for the list repreesentation, columns as main layouts, AsynImage for image representation with place holders, Texts, Cards and Dividers.
10. Material components for reusable colors, theming, animation and typography used in the app.
11. Unit tests were applied in the repository and view model to check the list representation, getting a coin information by Id and testing the states of the view model.
- DESIGN PATTERS:
1.  Builder - Retrofit API as a creational pattern.
2.  Dependency Injection - Dagger Hilt for simplicity as it works well with the VM.
3.  Singleton pattern as dependency injection modules.
4.  Factory - Gson converter factory in Retrofit for parsing HTTP responses into an object.
5.  Facade - GsonConverterFactory, working behind the scenes as a JSON deserializer.
6.  States - Added along VM to propagate a wrap the flow results.
- APP ARCHITECTURE:
1. Model View ViewModel - Stores data and updates the views and models accordingly.
2. Clean architecture - Data, Business logic, Presentation - VM and UI layers.
- TECHNOLOGIES USED AND DEPENDENCIES:
1. Kotlin - Main language of programming.
2. Jetpack Compose - Jetpack Compose is Android’s modern toolkit for building native UI.
3. Coroutines - Fetching async data along flow.
4. Flow - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
5. Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps.
6. ViewModel - Stores UI-related data that isn't destroyed on UI changes.
7. Dependency Injection - Hilt - Easier way to incorporate Dagger DI into Android apps.
8. Retrofit - A type-safe HTTP client for Android and Java.
9. Room database for caching data within the app and representing some data faster.
10. Material Components - Theming, layouts and animations.
11. Mockito - Main testing tool for mocking object injections and unit testing the presentation and repositories of the project.
