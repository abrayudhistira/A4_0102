package abrayudhistira.cobafinal.ui.navigasi

interface DestinasiNavigasi{
    val route : String
    val titleRes : String
}
object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = "Home Property"
}