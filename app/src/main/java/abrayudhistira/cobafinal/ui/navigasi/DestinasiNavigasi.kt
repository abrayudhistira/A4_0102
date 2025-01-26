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
object DestinasiEntryProperti : DestinasiNavigasi {
    override val route = "entry_properti"
    override val titleRes = "Entry Properti"
}
object DestinasiDetailProperti : DestinasiNavigasi {
    override val route = "detail_properti"
    override val titleRes = "Detail Properti"
    const val idPropertiArg = "id_properti"
    val routeWithArgs = "$route/{$idPropertiArg}"
}

object DestinasiHomeJenisProperty : DestinasiNavigasi {
    override val route = "home_jenis_property"
    override val titleRes = "Home Jenis Property"
}
object DestinasiInsertJenisProperty : DestinasiNavigasi {
    override val route = "insert_jenis_property"
    override val titleRes = "Insert Jenis Property"
}
object DestinasiUpdateJenisProperti : DestinasiNavigasi {
    override val route = "update_jenis_property"
    override val titleRes = "Update Property"
    const val idJenisArg = "idJenis"
    val routewithArgument = "$route/{$idJenisArg}"
}
object DestinasiDetailJenisProperti : DestinasiNavigasi {
    override val route = "detail_jenis_properti"
    override val titleRes = "Detail Jenis Property"
    const val idJenisArg = "idJenis"
    val routewithArgument = "$route/{$idJenisArg}"
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
object DestinasiEntryManajemenProperti : DestinasiNavigasi {
    override val route = "entry_manajemen_properti"
    override val titleRes = "Entry Manajemen Properti"
}
object DestinasiUpdateManajemenProperti : DestinasiNavigasi {
    override val route = "update_manajemen_properti"
    override val titleRes = "Update Manajemen Properti"
    const val idManajerArg = "idManajer"
    val routewithArgument = "$route/{$idManajerArg}"
}
object DestinasiDetailManajemenProperti : DestinasiNavigasi {
    override val route = "detail_manajemen_properti"
    override val titleRes = "Detail Manajemen Properti"
    const val idManajerArg = "idManajer"
    val routewithArgument = "$route/{$idManajerArg}"
}