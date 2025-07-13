package dev.onecoffeeplz.zamechalka.di

import dev.onecoffeeplz.zamechalka.domain.model.Note
import dev.onecoffeeplz.zamechalka.presentation.viewmodel.CreateNoteViewModel
import dev.onecoffeeplz.zamechalka.presentation.viewmodel.NoteDetailsViewModel
import dev.onecoffeeplz.zamechalka.presentation.viewmodel.NotesListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::CreateNoteViewModel)
    viewModelOf(::NotesListViewModel)
    viewModel { (note: Note) ->
        NoteDetailsViewModel(
            get(),
            get(),
            get(),
            get(),
            get(),
            note.path
        )
    }
}