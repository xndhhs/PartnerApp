package ro.partnerapp.injected

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import ro.partnerapp.interactors.PartnerAppUseCase
import ro.partnerapp.interactors.PartnerAppUseCaseImpl
import ro.partnerapp.repository.PartnerAppRepository
import ro.partnerapp.repository.PartnerAppRepositoryImpl
import ro.partnerapp.services.PartnerAppAPIService
import ro.partnerapp.services.PartnerAppService
import ro.partnerapp.services.PartnerAppServiceImpl
import ro.partnerapp.services.RetrofitClientFactory
import ro.partnerapp.ui.partner_app.details_screen.PartnerDetailsViewModel
import ro.partnerapp.ui.partner_app.main_screen.PartnerAppMainScreenViewModel


    val apiModule = module {
        single { RetrofitClientFactory.create() }
        single { get<Retrofit>().create(PartnerAppAPIService::class.java) }
    }

    val servicesModule = module {
        factory<PartnerAppService> {
            PartnerAppServiceImpl(
                partnerAppAPIService = get()
            )
        }
    }

    val repositoryModule = module {
        factory<PartnerAppRepository> {
            PartnerAppRepositoryImpl(
                partnerAppService = get()
            )
        }
    }

    val useCaseModule = module {
        factory<PartnerAppUseCase> {
            PartnerAppUseCaseImpl(
                partnerAppRepository = get()
            )
        }
    }

    val viewModelModule = module {
        viewModel {
            PartnerAppMainScreenViewModel(
                partnerAppUseCase = get()
            )
        }
        viewModel {
            PartnerDetailsViewModel(
                partnerAppUseCase = get(),
                savedStateHandle = get ()
            )
        }
    }

    val appModules = listOf(apiModule, useCaseModule, viewModelModule, repositoryModule, servicesModule)
