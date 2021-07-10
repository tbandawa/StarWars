package starwars.data.model

interface BaseResource {
    val created : String
    val edited : String
    val url : String
    val pilots : List<String>?
    val films : List<String>?
    val characters : List<String>?
    val planets : List<String>?
    val starships : List<String>?
    val vehicles : List<String>?
    val species : List<String>?
    val residents : List<String>?
    val people : List<String>?
}