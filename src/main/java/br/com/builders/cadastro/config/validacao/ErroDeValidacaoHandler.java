package br.com.builders.cadastro.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroDeValidacaoHandler {//interceptador: config spring para sempre que houver exception em algum mét de qlq controller, ele chama esse intercpetador passando a lista com os erros que aconteceram

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception) {// dentro do objeto exception tem todos os erros que aconteceram
																						
		List<ErroDeFormularioDto> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();// contém todos os erros de fields/formulários
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
			dto.add(erro);
		});

		return dto; // retorno: uma lista com os erros que aconteceram

	}

}
