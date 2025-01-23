package abrayudhistira.cobafinal.ui

import abrayudhistira.cobafinal.application.ManajemenPropertyApplication
import abrayudhistira.cobafinal.ui.jenisproperti.view.HomeJenisPropertiView
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.HomeJenisPropertiViewModel
import abrayudhistira.cobafinal.ui.property.HomePropertyViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory

object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer {
            HomePropertyViewModel(
                ManajemenPropertyApplications().container.propertyRepository)
        }
        initializer {
            HomeJenisPropertiViewModel(
                ManajemenPropertyApplications().container.jenisPropertyRepository)
        }
    }
}

fun CreationExtras.ManajemenPropertyApplications(): ManajemenPropertyApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ManajemenPropertyApplication)