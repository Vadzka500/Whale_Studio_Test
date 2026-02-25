package com.example.whale_test.features.signup.signup.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whale_test.R
import com.example.whale_test.features.information.presentation.formatToUi
import com.example.whale_test.ui.theme.BackColor
import com.example.whale_test.ui.theme.PromotionCardBackground
import com.example.whale_test.ui.theme.Sf_ui
import com.example.whale_test.ui.theme.TextColorBlack
import com.example.whale_test.ui.theme.TextColorGray
import com.example.whale_test.ui.theme.TextFieldOutlineColor
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


data class SignUpState(
    val isPhoneError: Boolean = false,
    val phoneErrorText: String = "",
    val isNameError: Boolean = false,
    val nameErrorText: String = ""
)

private const val AgreementProcessingTag = "agreement_processing"


@Composable
fun SignUpScreen(
    state: SignUpState,
    onBack: () -> Unit,
    toNextScreen: () -> Unit,
    modifier: Modifier = Modifier
) {

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var date by remember { mutableStateOf<LocalDate?>(null) }

    Box(modifier = modifier) {

        BackBlock(onBack = onBack)

        val scroll = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(top = 100.dp)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scroll)
        ) {

            Text(
                stringResource(R.string.signup_header_title),
                fontFamily = Sf_ui,
                fontWeight = FontWeight.SemiBold,
                fontSize = 36.sp,
                lineHeight = 44.sp,
                modifier = Modifier.padding(top = 56.dp)
            )
            Text(
                stringResource(R.string.signup_header_text),
                fontFamily = Sf_ui,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = TextColorGray,
                modifier = Modifier.padding(top = 8.dp)
            )

            Text(
                stringResource(R.string.phone_text),
                fontFamily = Sf_ui,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = TextColorGray,
                modifier = Modifier.padding(top = 24.dp)
            )

            BasicTextField(
                phone,
                onValueChange = { phone = it },
                label = stringResource(R.string.phone_placeholder),
                isError = state.isPhoneError,
                keyboardType = KeyboardType.Phone,
                errorText = state.phoneErrorText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
            Text(
                text = stringResource(R.string.name_text),
                fontFamily = Sf_ui,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = TextColorGray,
                modifier = Modifier.padding(top = 8.dp)
            )

            BasicTextField(
                name,
                onValueChange = { name = it },
                label = stringResource(R.string.name_placeholder_text),
                isError = state.isNameError,
                keyboardType = KeyboardType.Text,
                errorText = state.nameErrorText,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )

            Text(
                text = stringResource(R.string.birthday_text),
                fontFamily = Sf_ui,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = TextColorGray, modifier = Modifier.padding(top = 8.dp)
            )

            DateTextField(
                date, onValueChange = { date = it },
                stringResource(R.string.date_placeholder),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )

            AgreementProcessingBlock()

            SignUpButton()

        }
    }
}

@Composable
fun AgreementProcessingBlock(modifier: Modifier = Modifier) {
    val textAgreementProcessing = getAgreementProcessingText()
    var agreementProcessing by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.padding(top = 16.dp)
    ) {
        Checkbox(
            checked = agreementProcessing,
            onCheckedChange = { agreementProcessing = !agreementProcessing })
        Text(
            text = textAgreementProcessing,
            fontFamily = Sf_ui,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            modifier = Modifier.pointerInput(Unit) {
                detectTapGestures { offset: Offset ->

                    textAgreementProcessing.getStringAnnotations(
                        start = 0,
                        end = textAgreementProcessing.length
                    ).last().let { annotation ->
                        when (annotation.tag) {
                            AgreementProcessingTag -> {

                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun SignUpButton(modifier: Modifier = Modifier) {
    Button(
        onClick = {},
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
            .height(56.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            PromotionCardBackground
        )
    ) {
        Text(
            stringResource(R.string.singup_button_text), color = Color.Black,
            fontFamily = Sf_ui,
            fontWeight = FontWeight.SemiBold,
            fontSize = 16.sp,
        )
    }
}

@Composable
fun BackBlock(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(top = 60.dp)
            .padding(start = 16.dp)
            .clickable {
                onBack()
            }
    ) {
        Image(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                BackColor
            ),
            modifier = Modifier.size(36.dp)
        )
        Text(
            stringResource(R.string.back_text),
            fontFamily = Sf_ui,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = BackColor
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isError: Boolean,
    keyboardType: KeyboardType,
    errorText: String,
    modifier: Modifier
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                label,
                fontFamily = Sf_ui,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                color = TextColorGray
            )
        },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = TextFieldOutlineColor,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            errorIndicatorColor = Color.Red,
            errorTextColor = Color.Red,
            errorContainerColor = Color.White

        ),
        supportingText = {
            if (isError) {
                Text(
                    errorText,
                    fontFamily = Sf_ui,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                )
            }
        },
        isError = isError,
        singleLine = true,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTextField(
    value: LocalDate?,
    onValueChange: (LocalDate) -> Unit,
    label: String,
    modifier: Modifier = Modifier
) {

    var showDialog by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        value = value?.formatToUi() ?: "",
        onValueChange = {},
        placeholder = {
            Text(
                label,
                fontFamily = Sf_ui,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                color = TextColorGray
            )
        },
        shape = RoundedCornerShape(8.dp),
        readOnly = true,
        enabled = false,
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = TextFieldOutlineColor,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            disabledTextColor = TextColorBlack
        ),

        modifier = modifier
            .clickable {
                focusManager.clearFocus()
                showDialog = true
            }

    )

    if (showDialog) {

        val datePickerState = getDatePickerState()

        DatePickerDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { millis ->
                            val selectedDate = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()

                            onValueChange(selectedDate)
                        }
                        showDialog = false

                    }
                ) {
                    Text(stringResource(R.string.date_time_picker_select_text))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text(stringResource(R.string.date_time_picker_dismiss_text))
                }
            }
        ) {
            DatePicker(
                state = datePickerState,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun getDatePickerState(): DatePickerState {
    val currentYear = LocalDate.now().year
    return rememberDatePickerState(
        yearRange = 1900..currentYear,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val selected = Instant.ofEpochMilli(utcTimeMillis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()

                return !selected.isAfter(LocalDate.now())
            }
        }
    )
}

@Composable
fun getAgreementProcessingText(): AnnotatedString =
    buildAnnotatedString {
        append(stringResource(R.string.agreement_processing_first_text))
        append(" ")

        pushStringAnnotation(
            tag = AgreementProcessingTag,
            annotation = AgreementProcessingTag
        )

        withStyle(style = SpanStyle(color = BackColor)) {
            append(stringResource(R.string.agreement_processing_end_text))
        }
    }

