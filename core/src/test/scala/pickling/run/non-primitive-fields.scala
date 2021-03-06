package scala.pickling.non.primitive.fields

import org.scalatest.FunSuite
import scala.pickling._, scala.pickling.Defaults._, json._

case class Person(val name: String, val age: Int)
case class Philipp(val nationality: String, val weird: Boolean, val mother: Person)

class NonPrimitiveFieldsTest extends FunSuite {
  test("main") {
    val ph = new Philipp("German", true, new Person("Gudrun", 62))
    val ppickle = ph.pickle
    assert(ppickle.value === """
      |{
      |  "$type": "scala.pickling.non.primitive.fields.Philipp",
      |  "nationality": "German",
      |  "weird": true,
      |  "mother": {
      |    "name": "Gudrun",
      |    "age": 62
      |  }
      |}
    """.stripMargin.trim)
    assert(ppickle.unpickle[Philipp] === ph)
  }
}
