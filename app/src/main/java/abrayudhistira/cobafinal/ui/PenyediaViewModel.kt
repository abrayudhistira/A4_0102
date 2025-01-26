package abrayudhistira.cobafinal.ui

import abrayudhistira.cobafinal.application.ManajemenPropertyApplication
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.DetailJenisPropertiViewModel
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.HomeJenisPropertiViewModel
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.InsertJenisPopertiViewModel
import abrayudhistira.cobafinal.ui.jenisproperti.viewmodel.UpdateJenisPropertiViewModel
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.DetailManajemenPropertiViewModel
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.HomeManajerPropertiViewModel
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.InsertManajemenPopertiViewModel
import abrayudhistira.cobafinal.ui.manajerproperti.viewmodel.UpdateManajemenPropertiViewModel
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.DetailPemilikViewModel
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.HomePemilikViewModel
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.InsertPemilikViewModel
import abrayudhistira.cobafinal.ui.pemilik.viewmodel.UpdatePemilikViewModel
import abrayudhistira.cobafinal.ui.properti.viewmodel.InsertPropertiViewModel
import abrayudhistira.cobafinal.ui.property.viewmodel.DetailPropertyViewModel
import abrayudhistira.cobafinal.ui.property.viewmodel.HomePropertyViewModel
import abrayudhistira.cobafinal.ui.property.viewmodel.UpdatePropertiViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
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
            DetailPropertyViewModel(
                createSavedStateHandle(),
                ManajemenPropertyApplications().container.propertyRepository,
                ManajemenPropertyApplications().container.jenisPropertyRepository,
                ManajemenPropertyApplications().container.pemilikRepository,
                ManajemenPropertyApplications().container.manajerPropertyRepository
                )
        }
        initializer {
            InsertPropertiViewModel(
                createSavedStateHandle(),
                ManajemenPropertyApplications().container.propertyRepository,
                ManajemenPropertyApplications().container.jenisPropertyRepository,
                ManajemenPropertyApplications().container.pemilikRepository,
                ManajemenPropertyApplications().container.manajerPropertyRepository
            )
        }
        initializer {
            HomeJenisPropertiViewModel(
                ManajemenPropertyApplications().container.jenisPropertyRepository)
        }
        initializer {
            UpdatePropertiViewModel(
                createSavedStateHandle(),
                ManajemenPropertyApplications().container.propertyRepository,
                ManajemenPropertyApplications().container.jenisPropertyRepository,
                ManajemenPropertyApplications().container.pemilikRepository,
                ManajemenPropertyApplications().container.manajerPropertyRepository
            )
        }
        initializer {
            InsertJenisPopertiViewModel(
                createSavedStateHandle(),
                ManajemenPropertyApplications().container.jenisPropertyRepository)
        }
        initializer {
            DetailJenisPropertiViewModel(
                createSavedStateHandle(),
                ManajemenPropertyApplications().container.jenisPropertyRepository)
        }
        initializer {
            UpdateJenisPropertiViewModel(
                createSavedStateHandle(),
                ManajemenPropertyApplications().container.jenisPropertyRepository)
        }
        initializer {
            HomePemilikViewModel(
                ManajemenPropertyApplications().container.pemilikRepository)
        }
        initializer {
            InsertPemilikViewModel(
                createSavedStateHandle(),
                ManajemenPropertyApplications().container.pemilikRepository)
        }
        initializer {
            DetailPemilikViewModel(
                createSavedStateHandle(),
                ManajemenPropertyApplications().container.pemilikRepository
            )
        }
        initializer {
            UpdatePemilikViewModel(
                createSavedStateHandle(),
                ManajemenPropertyApplications().container.pemilikRepository
            )
        }
        initializer {
            HomeManajerPropertiViewModel(
                ManajemenPropertyApplications().container.manajerPropertyRepository)
        }
        initializer {
            InsertManajemenPopertiViewModel(
                createSavedStateHandle(),
                ManajemenPropertyApplications().container.manajerPropertyRepository)
        }
        initializer {
            UpdateManajemenPropertiViewModel(
                createSavedStateHandle(),
                ManajemenPropertyApplications().container.manajerPropertyRepository)
        }
        initializer {
            DetailManajemenPropertiViewModel(
                createSavedStateHandle(),
                ManajemenPropertyApplications().container.manajerPropertyRepository)
        }
    }
}

fun CreationExtras.ManajemenPropertyApplications(): ManajemenPropertyApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ManajemenPropertyApplication)