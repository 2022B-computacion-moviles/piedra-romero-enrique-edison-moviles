package com.example.proyecto.data.util

import com.example.proyecto.data.entity.Instrument

class DataInstruments {

    companion object {
        var instrumentsData = ArrayList<Instrument>()

        init {
            instrumentsData.add(Instrument(
                1,
                "EPIPHONE EAC3ANCH1 CLASSICAL E1 3/4-SIZE (NYLON 1.88″NUT) ANTIQUE NATURAL",
                "CUERDA",
                "La guitarra acústica de tamaño 3/4 Classical E1 tiene todas las características innovadoras de Classical E1 en un tamaño 3/4 más pequeño y fácil de sostener. Hecho con un cedro selecto con refuerzo personalizado PRO-Prietary ?, un mástil en forma de «D» EZ-Profile ? de caoba, trastes JumboPRO ? fáciles de presionar hacia abajo y cuerdas clásicas ultraligeras. Bolsa de concierto clásica opcional que se vende por separado. Epiphone respalda cada guitarra que fabrican con una garantía limitada de por vida y el servicio de atención al cliente de Gibson Brands.",
                12,
                450.5,
                "https://lavictoria.ec/wp-content/uploads/2023/01/GUITARRA-CLASICA-YAMAHA-C80-1-600x600.jpg"))

            instrumentsData.add(Instrument(
                2,
                "EPIPHONE ENPTEBNH1 Les Paul Studio E1 Ebony",
                "CUERDA",
                "Epiphone presenta la nueva Les Paul? Studio E1, una increíble introducción a la mejor guitarra eléctrica del mundo que es asequible para todos. La Les Paul Studio E1 tiene todas las características clásicas de «Les Paul», incluida una tapa de caoba tallad",
                12,
                444.04,
                "https://masmusika.com/wp-content/uploads/img/p/I06522615-001-300x300.jpg?v=1665787397"))

            instrumentsData.add(Instrument(
                3,
                "EPIPHONE ENPTHSNH1 Les Paul Studio E1 Cherry Sunburst",
                "CUERDA",
                "Epiphone presenta la nueva Les Paul? Studio E1, una increíble introducción a la mejor guitarra eléctrica del mundo que es asequible para todos. La Les Paul Studio E1 tiene todas las características clásicas de «Les Paul», incluida una tapa de caoba",
                12,
                400.2,
                "https://promarketgo.com/wp-content/uploads/2021/01/700001525.png"))

            instrumentsData.add(Instrument(
                4,
                "EPIPHONE ENPTVSNH1 Les Paul Studio E1 Vintage Sunburst",
                "CUERDA",
                "Epiphone presenta la nueva Les Paul? Studio E1, una increíble introducción a la mejor guitarra eléctrica del mundo que es asequible para todos. La Les Paul Studio E1 tiene todas las características clásicas de «Les Paul», incluida una tapa de caoba",
                12,
                450.5,
                "https://http2.mlstatic.com/D_NQ_NP_898050-MEC52086905325_102022-W.jpg"))

            instrumentsData.add(Instrument(
                5,
                "EPIPHONE PPAG-EA10NANH1 Songmaker Acoustic Guitar Player Natural",
                "CUERDA",
                "La Songmaker DR-100 ha sido durante mucho tiempo la guitarra acústica más vendida de Epiphone y tiene el aspecto, el sonido y la calidad de construcción que los músicos primerizos y los profesionales esperan al elegir una Epiphone. Con una tapa de abeto selecto, afinadores premium y un cuerpo de caoba. Epiphone respalda cada guitarra que fabrica con una garantía limitada de por vida y el servicio de atención al cliente de Gibson Brands?. El estuche rígido o EpiLite? se vende por separado. Epiphone – Para cada etapa. (Anteriormente llamado DR-100)",
                12,
                310.04,
                "https://sonikaecuador.com/23341-large_default/guitarra-elec-cort-cr100-negra.jpg"))

            instrumentsData.add(Instrument(
                6,
                "FENDER 014-7392-300 PP ACT METEORA BASS MN 3TSB",
                "CUERDA",
                "Fender Meteora Bass. Este nuevo modelo también es bastante diferente en cuanto a la electrónica típica que solemos asociar con Fender. Como el Dimension, lleva pastillas humbucker y electrónica activa. Estas pastillas las han llamado Fireball? Bass Humbucking. El puente es el típico «Hi-mass» de Fender y el diapasón tiene los tradicionales 20 trastes. El diapasón es algo más plano que otros modelos más tradicionales y la forma del cuerpo es un diseño «offset» un tanto angulado. No hemos localizado por ahora una versión en cinco cuerdas.",
                12,
                1742.66,
                "https://http2.mlstatic.com/D_NQ_NP_663364-MLA44504104127_012021-O.jpg"))

        }
    }
}