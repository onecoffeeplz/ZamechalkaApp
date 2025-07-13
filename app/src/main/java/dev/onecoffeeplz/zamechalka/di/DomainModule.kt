package dev.onecoffeeplz.zamechalka.di

import dev.onecoffeeplz.zamechalka.domain.usecase.view.CreateNoteUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.view.DeleteNoteUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.recording.DeleteRecordingUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.GetAudioPlaybackPositionUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.view.GetNoteByIdUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.view.GetNotesUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.PauseAudioPlayerUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.PrepareAudioPlayerUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.ReleaseAudioPlayerUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.playback.StartAudioPlayerUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.recording.StartRecordingUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.recording.StopRecordingUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.view.UpdateNoteUseCase
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.view.CreateNoteUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.view.DeleteNoteUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.recording.DeleteRecordingUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.playback.GetAudioPlaybackPositionUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.view.GetNoteByIdUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.view.GetNotesUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.playback.PauseAudioPlayerUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.playback.PrepareAudioPlayerUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.playback.ReleaseAudioPlayerUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.playback.StartAudioPlayerUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.recording.StartRecordingUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.recording.StopRecordingUseCaseImpl
import dev.onecoffeeplz.zamechalka.domain.usecase.impl.view.UpdateNoteUseCaseImpl
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