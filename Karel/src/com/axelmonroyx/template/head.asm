;/StartHeader
INCLUDE macros.mac
DOSSEG
.MODEL SMALL
STACK 100h
.DATA
			BUFFER		DB 8 DUP('$')  ;23h
			BUFFERTEMP	DB 8 DUP('$')  ;23h
			BLANCO	DB '#'
			BLANCOS	DB '$'
			MENOS	DB '-$'
			COUNT	DW 0
			NEGATIVO	DB 0
			ARREGLO	DW 0
			ARREGLO1	DW 0
			ARREGLO2	DW 0
			LISTAPAR	LABEL BYTE
			LONGMAX	DB 254
			TOTCAR	DB ?
			INTRODUCIDOS	DB 254 DUP ('$')
			MULT10	DW 1
			t1 dw ?
			t2 dw ?
			t3 dw ?
			cadena db '','$'
			turnoff db 'turnoff','$'
            turnleft db 'turnleft','$'
            move db 'move','$'
            pickbeeper db 'pickbeeper','$'
            putbeeper db 'putbeeper','$'



.CODE
.386
BEGIN:
			MOV     AX, @DATA
			MOV     DS, AX
CALL  COMPI
			MOV AX, 4C00H
			INT 21H
COMPI  PROC

