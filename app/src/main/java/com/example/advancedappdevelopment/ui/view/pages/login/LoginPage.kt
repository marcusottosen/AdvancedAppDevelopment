package com.example.advancedappdevelopment.ui.view.pages.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.advancedappdevelopment.data.model.NavigationRoute
import com.example.advancedappdevelopment.ui.viewmodel.LoginViewModel


@Composable
fun LoginPage(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    val context = LocalContext.current

    val loading: Boolean by viewModel.loading.observeAsState(initial = false)
    val loginSuccess: Boolean by viewModel.loginSuccess.observeAsState(initial = false)
    val showLoginSucess: Boolean by viewModel.showLoginSuccess.observeAsState(initial = false)

    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        // Disable page when loading and display icon
        if (loading) { CircularProgressIndicator(color = Color.Magenta) }
        else {
            if (!loginSuccess and showLoginSucess) {
                Toast.makeText(context, "Noget gik galt. Tjek venligst at oplysninger er korrekt", Toast.LENGTH_LONG).show()
                viewModel._showLoginSuccess.value = false
            }
            if (loginSuccess and showLoginSucess) {
                Toast.makeText(context, "Velkommen tilbage. Kør forsigtigt", Toast.LENGTH_LONG).show()
                viewModel._showLoginSuccess.value = false
                navController.navigate(NavigationRoute.LoadFromDB.route)
            }
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .background(Color.Transparent)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Godt at se dig igen",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                        )

                        //Spacer
                        Spacer(modifier = Modifier.height(50.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .width(340.dp)
                                .height(60.dp)
                                .background(Color.White, RoundedCornerShape(50)),
                            value = email,
                            onValueChange = { viewModel.updateEmail(it) },
                            placeholder = { Text(text = "Email") },
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Magenta,
                                unfocusedBorderColor = Color.Black
                            ),
                            leadingIcon = {
                                Icon(imageVector = Icons.Outlined.Email, contentDescription = "Email")
                            },
                        )

                        //Spacer
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .width(340.dp)
                                .height(60.dp)
                                .background(Color.White, RoundedCornerShape(50)),
                            value = password,
                            onValueChange = { viewModel.updatePassword(it) },
                            placeholder = { Text(text = "Adgangskode") },
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Magenta,
                                unfocusedBorderColor = Color.Black
                            ),
                            leadingIcon = {
                                Icon(imageVector = Icons.Outlined.Lock, contentDescription = "Adgangskode")
                            },
                            visualTransformation = PasswordVisualTransformation()
                        )

                        //Spacer
                        Spacer(modifier = Modifier.height(50.dp))


                        if ((email.isNotEmpty()) and (password.length >=6)) {
                            // Enabled button
                            Button(
                                onClick = { viewModel.loginUser() },
                                modifier = Modifier
                                    .width(340.dp)
                                    .height(60.dp)
                                    .border(1.dp, Color.Black, RoundedCornerShape(50)),
                                shape = RoundedCornerShape(50),
                                elevation = null,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Black
                                )
                            ) {
                                Text(
                                    text = "Login",
                                    color = Color.White
                                )
                            }
                        } else {
                            // Disabled button
                            Button(
                                onClick = {
                                    // handle click events
                                },
                                interactionSource = NoRippleInteractionSource(),
                                modifier = Modifier
                                    .width(340.dp)
                                    .height(60.dp)
                                    .border(1.dp, Color.Gray, RoundedCornerShape(50)),
                                shape = RoundedCornerShape(50),
                                elevation = null,
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.Gray
                                )
                            ) {
                                Text(text = "Login",
                                    color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}