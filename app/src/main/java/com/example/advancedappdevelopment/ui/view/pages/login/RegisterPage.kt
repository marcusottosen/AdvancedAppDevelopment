package com.example.advancedappdevelopment.ui.view.pages.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
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
import com.example.advancedappdevelopment.data.model.dataClass.CurrentUser
import com.example.advancedappdevelopment.ui.viewmodel.RegisterViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

// Register with ViewModel, but with two functions in onClick



@Composable
fun RegisterPage(navController: NavController, viewModel: RegisterViewModel = viewModel()) {
    val context = LocalContext.current

    val loading: Boolean by viewModel.loading.observeAsState(initial = false)
    val registerSuccess: Boolean by viewModel.registerSuccess.observeAsState(initial = false)
    val showRegisterSucess: Boolean by viewModel.showRegisterSuccess.observeAsState(initial = false)

    val name: String by viewModel.name.observeAsState("")
    val email: String by viewModel.email.observeAsState("")
    val password: String by viewModel.password.observeAsState("")
    val passwordCheck: String by viewModel.passwordCheck.observeAsState("")

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        // Disable page when loading and display icon
        if (loading) { CircularProgressIndicator(color = Color.Magenta) }
        else {
            if (!registerSuccess and showRegisterSucess) {
                Toast.makeText(context, "Noget gik galt. Tjek venligst at email er korrekt", Toast.LENGTH_LONG).show()
                viewModel._showRegisterSuccess.value = false
            }
            if (registerSuccess and showRegisterSucess) {
                Toast.makeText(context, "Du er nu oprettet. Kør forsigtigt", Toast.LENGTH_LONG).show()
                viewModel._showRegisterSuccess.value = false
                //TODO: Crasher pt. app'en at navigere til Homepage i authentication-branch
                //navController.navigate(NavigationRoute.LoginRegisterPage.route)
                //navController.navigate(NavigationRoute.Homepage.route)
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
                            text = "Tilmeld dig nemt, og kør nemt",
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
                            value = name,
                            onValueChange = { viewModel.updateName(it) },
                            placeholder = { Text(text = "Navn") },
                            shape = RoundedCornerShape(50),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Magenta,
                                unfocusedBorderColor = Color.Black
                            ),
                            leadingIcon = {
                                Icon(imageVector = Icons.Outlined.Person, contentDescription = "Navn")
                            },
                        )

                        //Spacer
                        Spacer(modifier = Modifier.height(10.dp))

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
                            placeholder = { Text(text = "Adgangskode (mindst 6 tegn)") },
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
                        Spacer(modifier = Modifier.height(10.dp))

                        OutlinedTextField(
                            modifier = Modifier
                                .width(340.dp)
                                .height(60.dp)
                                .background(Color.White, RoundedCornerShape(50)),
                            value = passwordCheck,
                            onValueChange = { viewModel.updatePasswordCheck(it) },
                            placeholder = { Text(text = "Gentag dgangskode") },
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
                        //TODO: Kun signup med kode over 6 karaktere.
                        //TODO: Toastbesked ved mismatchende kodeer.
                        //TODO: Toastbesked ved tommefelter i stedet for crash.



                        if ((name.isNotEmpty()) and (email.isNotEmpty()) and (password.length >=6) and (password == passwordCheck)) {
                            // Enabled button
                            Button(
                                onClick = { viewModel.registerUser() },
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
                                    text = "Tilmeld",
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
                                Text(text = "Tilmeld",
                                color = Color.White)
                            }
                        }
                    }
                }
            }
        }

    }
}

// Disable Ripples
// https://semicolonspace.com/jetpack-compose-disable-ripple-effect/#no-ripple-theme
class NoRippleInteractionSource : MutableInteractionSource {

    override val interactions: Flow<Interaction> = emptyFlow()

    override suspend fun emit(interaction: Interaction) {}

    override fun tryEmit(interaction: Interaction) = true
}