package ml.gamemodepvp.Modules.classes.kit

/**
 * Created by bfox1 on 6/2/2015.
 * In God We Trust.
 */
object DisplayItemAction {

  object Action extends Enumeration
  {
    type Action = Value

    val COMMAND, KIT, INVENTORY, ITEM, ACTIVE = Value
   // implicit def valuef(action:Action) = new ItemAction(action)
  }
  import Action._


 // class ItemAction(action:Action)
  //{
    def test(action:Action) = !test2(action:Action)
    def test2(action:Action) = Action match
    {
      case COMMAND | KIT => def test4(){}
      case _              => false
   // }

  //}

  //implicit def valuef(action:Action) = new ItemAction(action)









}
