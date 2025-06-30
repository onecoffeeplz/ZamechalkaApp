package dev.onecoffeeplz.zamechalka.di

import dev.onecoffeeplz.zamechalka.presentation.viewmodel.CreateNoteViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::CreateNoteViewModel)
}