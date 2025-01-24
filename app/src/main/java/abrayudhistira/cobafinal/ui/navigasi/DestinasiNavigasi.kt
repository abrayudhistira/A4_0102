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

object DestinasiHomeJenisProperty : DestinasiNavigasi {
    override val route = "home_jenis_property"
    override val titleRes = "Home Jenis Property"
}
object DestinasiInsertJenisProperty : DestinasiNavigasi {
    override val route = "insert_jenis_property"
    override val titleRes = "Insert Jenis Property"
}

object DestinasiHomePemilik : DestinasiNavigasi {
    override val route = "home_pemilik"
    override val titleRes = "Home Pemilik"
}

object DestinasiEntryPemilik : DestinasiNavigasi {
    override val route = "entry_pemilik"
    override val titleRes = "Entry Pemilik"
}
object DestinasiDetailPemilik : DestinasiNavigasi {
    override val route = "detail_pemilik"
    override val titleRes = "Detail Pemilik"
    const val idPemilikArg = "idPemilik"
    val routewithArgument = "$route/{$idPemilikArg}"
}
object DestinasiUpdatePemilik : DestinasiNavigasi {
    override val route = "update_pemilik"
    override val titleRes = "Update Pemilik"
    const val idPemilikArg = "idPemilik"
    val routewithArgument = "$route/{$idPemilikArg}"
}
object DestinasiHomeManajerProperty : DestinasiNavigasi {
    override val route = "home_manajer"
    override val titleRes = "Home Manajer Property"

}