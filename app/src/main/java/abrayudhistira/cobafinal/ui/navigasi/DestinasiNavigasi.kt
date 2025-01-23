package abrayudhistira.cobafinal.ui.navigasi

interface DestinasiNavigasi{
    val route : String
    val titleRes : String
}

object DestinasiMainScreen : DestinasiNavigasi {
    override val route = "mainscreen"
    override val titleRes = "Main Screen"
}

object DestinasiHomeProperty : DestinasiNavigasi {
    override val route = "home_property"
    override val titleRes = "Home Property"
}

object DestinasiEntry: DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Entry Property"
}

object DestinasiHomeJenisProperty : DestinasiNavigasi {
    override val route = "home_jenis_property"
    override val titleRes = "Home Jenis Property"
}
