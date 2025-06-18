package com.example.whitehelmettask.presentation.details

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.whitehelmettask.R
import com.example.whitehelmettask.presentation.details.state.DetailsUiState
import com.valentinilk.shimmer.shimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    movieId: Int,
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state by remember { derivedStateOf { viewModel.state } }
    val scrollState = rememberLazyListState()

    LaunchedEffect(movieId) {
        viewModel.loadMovieDetails(movieId)
    }

    val toolbarExpanded = scrollState.firstVisibleItemScrollOffset < 100
    val toolbarAlpha by animateFloatAsState(if (toolbarExpanded) 0f else 1f, label = "toolbarAlpha")
    val movie = state.movie

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        movie?.title.orEmpty(),
                        modifier = Modifier.alpha(toolbarAlpha),
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = toolbarAlpha)
                )
            )
        },
        containerColor = Color.Transparent
    ) { innerPadding ->

        when {
            state.isLoading -> {
                ShimmerDetailsContent()
            }

            state.error != null -> {
                Box(modifier = Modifier.fillMaxSize().statusBarsPadding(), contentAlignment = Alignment.Center) {
                    Text("Error: ${state.error}", color = MaterialTheme.colorScheme.error)
                }
            }

            movie != null -> {
                Box(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data("https://image.tmdb.org/t/p/w780${movie.backdropPath ?: movie.posterPath}")
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color.Transparent, Color.Black)
                                )
                            )
                    )

                    LazyColumn(
                        state = scrollState,
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data("https://image.tmdb.org/t/p/w342${movie.posterPath}")
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .width(120.dp)
                                        .height(180.dp)
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = movie.title,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = Color.White
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "${String.format("%.1f", movie.voteAverage)} ★",
                                        fontSize = 16.sp,
                                        color = Color.Yellow
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.baseline_calendar_month_24),
                                            contentDescription = null,
                                            tint = Color.White
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(text = movie.releaseDate, color = Color.White)
                                    }
                                }
                            }
                        }

                        item {
                            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                                movie.genres.forEach { genre ->
                                    Text(
                                        "• ${genre}",
                                        modifier = Modifier.padding(end = 8.dp),
                                        color = Color.White
                                    )
                                }
                            }
                        }

                        item {
                            Divider(modifier = Modifier.padding(vertical = 12.dp), color = Color.White)
                        }

                        item {
                            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                                if (movie.tagline.isNotEmpty()) {
                                    Text(text = movie.tagline, fontWeight = FontWeight.SemiBold, color = Color.White)
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                                Text(text = movie.overview ?: "No description", color = Color.White)

                                Spacer(modifier = Modifier.height(12.dp))

                                Text("Country: ${movie.originCountry.joinToString()}", color = Color.White)
                                Text("+18: ${if (movie.adult) "Yes" else "No"}", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerDetailsContent() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .statusBarsPadding()
                .shimmer()
                .background(Color.LightGray)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth().height(24.dp).shimmer().background(Color.LightGray))
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.fillMaxWidth().height(16.dp).shimmer().background(Color.LightGray))
        Spacer(modifier = Modifier.height(8.dp))
        Box(modifier = Modifier.fillMaxWidth().height(100.dp).shimmer().background(Color.LightGray))
    }
}