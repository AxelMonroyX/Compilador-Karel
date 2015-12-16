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

;COMPI
		JF 1,P1
		JF 1,P2
		WRITE pickbeeper
		WRITELN 
		JMP Q1
	P2:
		WRITE move
		WRITELN 
	Q1:
		JMP Q2
	P1:
		WRITE turnleft
		WRITELN 
	Q2:
		PUSH CX
		MOV CX,3
	S1:
		PUSH CX
		MOV CX,3
	S2:
		WRITE move
		WRITELN 
		loop S2
		POP CX
		loop S1
		POP CX
		JF 1,P3
		PUSH CX
		MOV CX,2
	S3:
		PUSH CX
		MOV CX,2
	S4:
		WRITE turnleft
		WRITELN 
		loop S4
		POP CX
		loop S3
		POP CX
		JMP Q3
	P3:
	Q3:
		ret
COMPI  ENDP
END BEGIN
