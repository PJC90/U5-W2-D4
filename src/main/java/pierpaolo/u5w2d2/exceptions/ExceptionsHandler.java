package pierpaolo.u5w2d2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pierpaolo.u5w2d2.payloads.ErrorDTO;


import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400 Bad Request se ad es. metti due post con lo stesso titolo (perchè ho messo il controllo nel metodo save!)
    public ErrorDTO handleBadRequest(BadRequestException ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)   //404 Not Found es. se passi un id che non esiste nel db
    public ErrorDTO HandleNotFound(NotFoundException ex){
        return new ErrorDTO(ex.getMessage(), LocalDateTime.now());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)   //500 Internal Server Error (per testarlo decommenta RuntimeException nel PostController)
    public ErrorDTO handleGenericError(Exception ex){
        ex.printStackTrace();
        return new ErrorDTO("Problema Lato Server! Giuro che risolveremo!!!!", LocalDateTime.now());
    }
}
