package dev.onecoffeeplz.zamechalka.di

import dev.onecoffeeplz.zamechalka.domain.usecase.CreateNoteUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.DeleteNoteUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.GetNoteByIdUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.GetNotesUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.StartRecordingUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.StopRecordingUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.UpdateNoteUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.CreateNoteUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.DeleteNoteUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.GetNoteByIdUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.GetNotesUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.StartRecordingUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.StopRecordingUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.UpdateNoteUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::CreateNoteUseCaseImpl) { bind<CreateNoteUseCase>() }
    factoryOf(::DeleteNoteUseCaseImpl) { bind<DeleteNoteUseCase>() }
    factoryOf(::GetNoteByIdUseCaseImpl) { bind<GetNoteByIdUseCase>() }
    factoryOf(::GetNotesUseCaseImpl) { bind<GetNotesUseCase>() }
    factoryOf(::UpdateNoteUseCaseImpl) { bind<UpdateNoteUseCase>() }
    factoryOf(::StartRecordingUseCaseImpl) { bind<StartRecordingUseCase>() }
    factoryOf(::StopRecordingUseCaseImpl) { bind<StopRecordingUseCase>() }
}