package app.dokt.app

class Foo(observer: (String) -> Unit) {
    private val observers = mutableListOf(observer)
    var bar by Observable("bar", observers)
}
