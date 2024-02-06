package lectures.part2oop

object Enums {

  enum Permissions {
    case READ, WRITE, EXECUTE, NONE
    //add fields/ methods
    def openDocument(): Unit =
      if(this == READ) println("opening document...")
      else println("reading not allowed.")
  }
  val somePermissions: Permissions = Permissions.READ

  //constructor args
  enum PermissonsWithBits(bits: Unit){
    case READ extends PermissonsWithBits(4) //100
    case WRITE extends PermissonsWithBits(2) //010
    case EXECUTE extends PermissonsWithBits(1) //001
    case NONE extends PermissonsWithBits(0) //000
  }

  object PermissonsWithBits{
    def fromBits(bits: Int): PermissonsWithBits = //whatever
      PermissonsWithBits.NONE
  }

  //standard API
  val somePermissionsOrdinal = somePermissions.ordinal
  val allPermissions = PermissonsWithBits.values //array of all possible values of the enum
  val readPermissions: Permissions = Permissions.valueOf("READ")  // Permisssions.READ

  def main(args: Array[String]): Unit = {
    somePermissions.openDocument()
    println(somePermissionsOrdinal)
  }
}
