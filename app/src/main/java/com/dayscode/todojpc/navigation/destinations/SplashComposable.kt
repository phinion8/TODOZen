package com.dayscode.todojpc.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.dayscode.todojpc.ui.screens.splash.SplashScreen
import com.dayscode.todojpc.util.Constants.LIST_SCREEN
import com.dayscode.todojpc.util.Constants.SPLASH_SCREEN

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.splashComposable(
    navigateToListScreen: () -> Unit
){
    composable(
        route = SPLASH_SCREEN,
        exitTransition = {
            when(targetState.destination.route){
                LIST_SCREEN ->
                    slideOutVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    )
                else -> null
            }

        }
    ){
        SplashScreen(navigateToListScreen = navigateToListScreen)
    }
}