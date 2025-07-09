package dev.onecoffeeplz.zamechalka.di

import dev.onecoffeeplz.zamechalka.domain.usecase.CreateNoteUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.DeleteNoteUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.DeleteRecordingUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.GetAudioPlaybackPositionUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.GetNoteByIdUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.GetNotesUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.PauseAudioPlayerUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.PrepareAudioPlayerUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.ReleaseAudioPlayerUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.StartAudioPlayerUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.StartRecordingUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.StopRecordingUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.UpdateNoteUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.CreateNoteUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.DeleteNoteUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.DeleteRecordingUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.GetAudioPlaybackPositionUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.GetNoteByIdUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.GetNotesUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.PauseAudioPlayerUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.PrepareAudioPlayerUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.ReleaseAudioPlayerUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.StartAudioPlayerUseCaseImpl
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
    factoryOf(::DeleteRecordingUseCaseImpl) { bind<DeleteRecordingUseCase>() }
    factoryOf(::GetAudioPlaybackPositionUseCaseImpl) { bind<GetAudioPlaybackPositionUseCase>() }
    factoryOf(::PauseAudioPlayerUseCaseImpl) { bind<PauseAudioPlayerUseCase>() }
    factoryOf(::PrepareAudioPlayerUseCaseImpl) { bind<PrepareAudioPlayerUseCase>() }
    factoryOf(::ReleaseAudioPlayerUseCaseImpl) { bind<ReleaseAudioPlayerUseCase>() }
    factoryOf(::StartAudioPlayerUseCaseImpl) { bind<StartAudioPlayerUseCase>() }
}