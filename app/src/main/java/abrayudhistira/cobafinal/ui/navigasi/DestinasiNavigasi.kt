package abrayudhistira.cobafinal.ui.navigasi

interface DestinasiNavigasi{
    val route : String
    val titleRes : String
}
object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Home Property"
}

object DestinasiEntry: DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Entry Property"
}

object DestinasiMainScreen : DestinasiNavigasi {
    override val route = "mainscreen"
    override val titleRes = "Main Screen"
}