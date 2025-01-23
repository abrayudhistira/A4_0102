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

object DestinasiHomePemilik : DestinasiNavigasi {
    override val route = "home_pemilik"
    override val titleRes = "Home Pemilik"
}

object DestinasiManajerProperty : DestinasiNavigasi {
    override val route = "home_manajer"
    override val titleRes = "Home Manajer Property"
}