package com.braininavet.felicaemu.converter

class B(arg4: ByteArray) {
    private val k: IntArray = IntArray(96)

    init {
        if (arg4.size != 24) {
            throw IllegalArgumentException("Key was not 24 bytes, instead was " + arg4.size)
        }

        this.a(arg4, 0, 0)
        this.a(arg4, 8, 32)
        this.a(arg4, 16, 64)
    }

    fun a(arg5: ByteArray): ByteArray {
        val v1 = 8
        if (arg5.size != v1) {
            throw IllegalArgumentException("Input was not 8 bytes long, instead was " + arg5.size)
        }

        val v0 = ByteArray(v1)
        a(v0, this.a(0, c(arg5)))
        a(v0, this.b(32, c(v0)))
        a(v0, this.a(64, c(v0)))
        return v0
    }

    private fun a(arg9: ByteArray, arg10: Int, arg11: Int) {
        val v4 = ByteArray(56)
        var v2: Int
        v2 = 0
        while (v2 < 8) {
            var v0 = 0
            while (v0 < 7) {
                v4[55 - v2 * 7 - v0] = (arg9[arg10 + v2].toInt()ushr(v0) and 1).toByte()
                ++v0
            }
            ++v2
        }

        var v0 = 0
        while (v0 < 32) {
            v2 = 0
            var v3 = 0
            while (v3 < 24) {
                v2 = v2 or (v4[a!![v0 * 24 + v3].toInt()].toInt() shl b!![(v0 and 1) * 24 + v3].toInt())
                ++v3
            }

            this.k[arg11 + v0] = v2
            ++v0
        }
    }

    private fun a(arg10: Int, arg11: Long): Long {
        val v8 = 28
        val v7 = 32
        var v0 = arg11.ushr(v7).toInt()
        var v2 = arg11.toInt()
        var v1 = 0
        while (v1 < v7) {
            var v3 = a(this.k[arg10 + v1 + 1] xor v0, v8)
            v2 = v2 xor (j[v3.ushr(2) and 63] xor (g[v3.ushr(26) and 63] xor h[v3.ushr(18) and 63] xor i[v3.ushr(10) and 63]) xor c[(this.k[arg10 + v1] xor v0).ushr(26) and 63] xor d[(this.k[arg10 + v1] xor v0).ushr(18) and 63] xor e[(this.k[arg10 + v1] xor v0).ushr(10) and 63] xor f[(this.k[arg10 + v1] xor v0).ushr(2) and 63])
            v3 = a(this.k[arg10 + v1 + 3] xor v2, v8)
            v0 = v0 xor (j[v3.ushr(2) and 63] xor (g[v3.ushr(26) and 63] xor h[v3.ushr(18) and 63] xor i[v3.ushr(10) and 63]) xor c[(this.k[arg10 + v1 + 2] xor v2).ushr(26) and 63] xor d[(this.k[arg10 + v1 + 2] xor v2).ushr(18) and 63] xor e[(this.k[arg10 + v1 + 2] xor v2).ushr(10) and 63] xor f[(this.k[arg10 + v1 + 2] xor v2).ushr(2) and 63])
            v1 += 4
        }

        return v0.toLong() shl v7 or (v2.toLong() and 4294967295L)
    }

    fun b(arg5: ByteArray): ByteArray {
        val v1 = 8
        if (arg5.size != v1) {
            throw IllegalArgumentException("Input was not 8 bytes long, instead was " + arg5.size)
        }

        val v0 = ByteArray(v1)
        a(v0, this.b(64, c(arg5)))
        a(v0, this.a(32, c(v0)))
        a(v0, this.b(0, c(v0)))
        return v0
    }

    private fun b(arg11: Int, arg12: Long): Long {
        val v9 = 28
        val v8 = 32
        var v0 = arg12.ushr(v8).toInt()
        var v2 = arg12.toInt()
        var v1 = 0
        while (v1 < v8) {
            var v3 = a(this.k[arg11 + 31 - v1] xor v0, v9)
            v2 = v2 xor (j[v3.ushr(2) and 63] xor (c[(this.k[arg11 + 30 - v1] xor v0).ushr(26) and 63] xor d[(this.k[arg11 + 30 - v1] xor v0).ushr(18) and 63] xor e[(this.k[arg11 + 30 - v1] xor v0).ushr(10) and 63] xor f[(this.k[arg11 + 30 - v1] xor v0).ushr(2) and 63] xor g[v3.ushr(26) and 63] xor h[v3.ushr(18) and 63] xor i[v3.ushr(10) and 63]))
            v3 = a(this.k[arg11 + 29 - v1] xor v2, v9)
            v0 = v0 xor (j[v3.ushr(2) and 63] xor (c[(this.k[arg11 + 28 - v1] xor v2).ushr(26) and 63] xor d[(this.k[arg11 + 28 - v1] xor v2).ushr(18) and 63] xor e[(this.k[arg11 + 28 - v1] xor v2).ushr(10) and 63] xor f[(this.k[arg11 + 28 - v1] xor v2).ushr(2) and 63] xor g[v3.ushr(26) and 63] xor h[v3.ushr(18) and 63] xor i[v3.ushr(10) and 63]))
            v1 += 4
        }

        return v0.toLong() shl v8 or (v2.toLong() and 4294967295L)
    }

    companion object {
        private var a: ByteArray? = null
        private var b: ByteArray? = null
        private val c: IntArray
        private val d: IntArray
        private val e: IntArray
        private val f: IntArray
        private val g: IntArray
        private val h: IntArray
        private val i: IntArray
        private val j: IntArray

        init {
            a = byteArrayOf(0x22, 0xd, 0x5, 0x2e, 0x2f, 0x12, 0x20, 0x29, 0xb, 0x35, 0x21, 0x14, 0xe, 0x24, 0x1e, 0x18, 0x31, 0x2, 0xf, 0x25, 0x2a, 0x32, 0x0, 0x15, 0x26, 0x30, 0x6, 0x1a, 0x27, 0x4, 0x34, 0x19, 0xc, 0x1b, 0x1f, 0x28, 0x1, 0x11, 0x1c, 0x1d, 0x17, 0x33, 0x23, 0x7, 0x3, 0x16, 0x9, 0x2b, 0x29, 0x14, 0xc, 0x35, 0x36, 0x19, 0x27, 0x30, 0x12, 0x1f, 0x28, 0x1b, 0x15, 0x2b, 0x25, 0x0, 0x1, 0x9, 0x16, 0x2c, 0x31, 0x2, 0x7, 0x1c, 0x2d, 0x37, 0xd, 0x21, 0x2e, 0xb, 0x6, 0x20, 0x13, 0x22, 0x26, 0x2f, 0x8, 0x18, 0x23, 0x24, 0x1e, 0x3, 0x2a, 0xe, 0xa, 0x1d, 0x10, 0x32, 0x37, 0x22, 0x1a, 0x26, 0xb, 0x27, 0x35, 0x5, 0x20, 0x2d, 0x36, 0x29, 0x23, 0x2, 0x33, 0xe, 0xf, 0x17, 0x24, 0x3, 0x8, 0x10, 0x15, 0x2a, 0x6, 0xc, 0x1b, 0x2f, 0x1f, 0x19, 0x14, 0x2e, 0x21, 0x30, 0x34, 0x4, 0x16, 0x7, 0x31, 0x32, 0x2c, 0x11, 0x1, 0x1c, 0x18, 0x2b, 0x1e, 0x9, 0xc, 0x30, 0x28, 0x34, 0x19, 0x35, 0x26, 0x13, 0x2e, 0x6, 0xb, 0x37, 0x31, 0x10, 0xa, 0x1c, 0x1d, 0x25, 0x32, 0x11, 0x16, 0x1e, 0x23, 0x1, 0x14, 0x1a, 0x29, 0x4, 0x2d, 0x27, 0x22, 0x1f, 0x2f, 0x5, 0xd, 0x12, 0x24, 0x15, 0x8, 0x9, 0x3, 0x0, 0xf, 0x2a, 0x7, 0x2, 0x2c, 0x17, 0x1a, 0x5, 0x36, 0xd, 0x27, 0x26, 0x34, 0x21, 0x1f, 0x14, 0x19, 0xc, 0x8, 0x1e, 0x18, 0x2a, 0x2b, 0x33, 0x9, 0x0, 0x24, 0x2c, 0x31, 0xf, 0x22, 0x28, 0x37, 0x12, 0x6, 0x35, 0x30, 0x2d, 0x4, 0x13, 0x1b, 0x20, 0x32, 0x23, 0x16, 0x17, 0x11, 0xe, 0x1d, 0x1, 0x15, 0x10, 0x3, 0x25, 0x28, 0x13, 0xb, 0x1b, 0x35, 0x34, 0xd, 0x2f, 0x2d, 0x22, 0x27, 0x1a, 0x16, 0x2c, 0x7, 0x1, 0x2, 0xa, 0x17, 0xe, 0x32, 0x3, 0x8, 0x1d, 0x30, 0x36, 0xc, 0x20, 0x14, 0x26, 0x5, 0x6, 0x12, 0x21, 0x29, 0x2e, 0x9, 0x31, 0x24, 0x25, 0x0, 0x1c, 0x2b, 0xf, 0x23, 0x1e, 0x11, 0x33, 0x36, 0x21, 0x19, 0x29, 0x26, 0xd, 0x1b, 0x4, 0x6, 0x30, 0x35, 0x28, 0x24, 0x3, 0x15, 0xf, 0x10, 0x18, 0x25, 0x1c, 0x9, 0x11, 0x16, 0x2b, 0x5, 0xb, 0x1a, 0x2e, 0x22, 0x34, 0x13, 0x14, 0x20, 0x2f, 0x37, 0x1f, 0x17, 0x8, 0x32, 0x33, 0xe, 0x2a, 0x2, 0x1d, 0x31, 0x2c, 0x0, 0xa, 0xb, 0x2f, 0x27, 0x37, 0x34, 0x1b, 0x29, 0x12, 0x14, 0x5, 0x26, 0x36, 0x32, 0x11, 0x23, 0x1d, 0x1e, 0x7, 0x33, 0x2a, 0x17, 0x0, 0x24, 0x2, 0x13, 0x19, 0x28, 0x1f, 0x30, 0xd, 0x21, 0x22, 0x2e, 0x4, 0xc, 0x2d, 0x25, 0x16, 0x9, 0xa, 0x1c, 0x1, 0x10, 0x2b, 0x8, 0x3, 0xe, 0x18, 0x12, 0x36, 0x2e, 0x5, 0x6, 0x22, 0x30, 0x19, 0x1b, 0xc, 0x2d, 0x4, 0x2, 0x18, 0x2a, 0x24, 0x25, 0xe, 0x3, 0x31, 0x1e, 0x7, 0x2b, 0x9, 0x1a, 0x20, 0x2f, 0x26, 0x37, 0x14, 0x28, 0x29, 0x35, 0xb, 0x13, 0x34, 0x2c, 0x1d, 0x10, 0x11, 0x23, 0x8, 0x17, 0x32, 0xf, 0xa, 0x15, 0x0, 0x20, 0xb, 0x1f, 0x13, 0x14, 0x30, 0x5, 0x27, 0x29, 0x1a, 0x6, 0x12, 0x10, 0x7, 0x1, 0x32, 0x33, 0x1c, 0x11, 0x8, 0x2c, 0x15, 0x2, 0x17, 0x28, 0x2e, 0x4, 0x34, 0xc, 0x22, 0x36, 0x37, 0x26, 0x19, 0x21, 0xd, 0x3, 0x2b, 0x1e, 0x0, 0x31, 0x16, 0x25, 0x9, 0x1d, 0x18, 0x23, 0xe, 0x2e, 0x19, 0x2d, 0x21, 0x22, 0x5, 0x13, 0x35, 0x37, 0x28, 0x14, 0x20, 0x1e, 0x15, 0xf, 0x9, 0xa, 0x2a, 0x0, 0x16, 0x3, 0x23, 0x10, 0x25, 0x36, 0x1f, 0x12, 0xd, 0x1a, 0x30, 0xb, 0xc, 0x34, 0x27, 0x2f, 0x1b, 0x11, 0x2, 0x2c, 0xe, 0x8, 0x24, 0x33, 0x17, 0x2b, 0x7, 0x31, 0x1c, 0x1f, 0x27, 0x6, 0x2f, 0x30, 0x13, 0x21, 0x26, 0xc, 0x36, 0x22, 0x2e, 0x2c, 0x23, 0x1d, 0x17, 0x18, 0x1, 0xe, 0x24, 0x11, 0x31, 0x1e, 0x33, 0xb, 0x2d, 0x20, 0x1b, 0x28, 0x5, 0x19, 0x1a, 0xd, 0x35, 0x4, 0x29, 0x0, 0x10, 0x3, 0x1c, 0x16, 0x32, 0xa, 0x25, 0x2, 0x15, 0x8, 0x2a, 0x2d, 0x35, 0x14, 0x4, 0x5, 0x21, 0x2f, 0x34, 0x1a, 0xb, 0x30, 0x1f, 0x3, 0x31, 0x2b, 0x25, 0x7, 0xf, 0x1c, 0x32, 0x0, 0x8, 0x2c, 0xa, 0x19, 0x6, 0x2e, 0x29, 0x36, 0x13, 0x27, 0x28, 0x1b, 0x26, 0x12, 0x37, 0xe, 0x1e, 0x11, 0x2a, 0x24, 0x9, 0x18, 0x33, 0x10, 0x23, 0x16, 0x1, 0x6, 0x26, 0x22, 0x12, 0x13, 0x2f, 0x4, 0xd, 0x28, 0x19, 0x5, 0x2d, 0x11, 0x8, 0x2, 0x33, 0x15, 0x1d, 0x2a, 0x9, 0xe, 0x16, 0x3, 0x18, 0x27, 0x14, 0x1f, 0x37, 0xb, 0x21, 0x35, 0x36, 0x29, 0x34, 0x20, 0xc, 0x1c, 0x2c, 0x0, 0x1, 0x32, 0x17, 0x7, 0xa, 0x1e, 0x31, 0x24, 0xf, 0x14, 0x34, 0x30, 0x20, 0x21, 0x4, 0x12, 0x1b, 0x36, 0x27, 0x13, 0x6, 0x0, 0x16, 0x10, 0xa, 0x23, 0x2b, 0x1, 0x17, 0x1c, 0x24, 0x11, 0x7, 0x35, 0x22, 0x2d, 0xc, 0x19, 0x2f, 0x26, 0xb, 0x37, 0xd, 0x2e, 0x1a, 0x2a, 0x3, 0xe, 0xf, 0x9, 0x25, 0x15, 0x18, 0x2c, 0x8, 0x32, 0x1d, 0x1b, 0x6, 0x37, 0x27, 0x28, 0xb, 0x19, 0x22, 0x4, 0x2e, 0x1a, 0xd, 0x7, 0x1d, 0x17, 0x11, 0x2a, 0x32, 0x8, 0x1e, 0x23, 0x2b, 0x18, 0xe, 0x1f, 0x29, 0x34, 0x13, 0x20, 0x36, 0x2d, 0x12, 0x5, 0x14, 0x35, 0x21, 0x31, 0xa, 0x15, 0x16, 0x10, 0x2c, 0x1c, 0x0, 0x33, 0xf, 0x2, 0x24)
            b = byteArrayOf(0x1f, 0x1e, 0x1d, 0x1c, 0x1b, 0x1a, 0x17, 0x16, 0x15, 0x14, 0x13, 0x12, 0xf, 0xe, 0xd, 0xc, 0xb, 0xa, 0x7, 0x6, 0x5, 0x4, 0x3, 0x2, 0x1b, 0x1a, 0x19, 0x18, 0x17, 0x16, 0x13, 0x12, 0x11, 0x10, 0xf, 0xe, 0xb, 0xa, 0x9, 0x8, 0x7, 0x6, 0x3, 0x2, 0x1, 0x0, 0x1f, 0x1e)
            c = intArrayOf(0x2080008, 0x2082000, 0x2008, 0x0, 0x2002000, 0x80008, 0x2080000, 0x2082008, 0x8, 0x2000000, 0x82000, 0x2008, 0x82008, 0x2002008, 0x2000008, 0x2080000, 0x2000, 0x82008, 0x80008, 0x2002000, 0x2082008, 0x2000008, 0x0, 0x82000, 0x2000000, 0x80000, 0x2002008, 0x2080008, 0x80000, 0x2000, 0x2082000, 0x8, 0x80000, 0x2000, 0x2000008, 0x2082008, 0x2008, 0x2000000, 0x0, 0x82000, 0x2080008, 0x2002008, 0x2002000, 0x80008, 0x2082000, 0x8, 0x80008, 0x2002000, 0x2082008, 0x80000, 0x2080000, 0x2000008, 0x82000, 0x2008, 0x2002008, 0x2080000, 0x8, 0x2082000, 0x82008, 0x0, 0x2000000, 0x2080008, 0x2000, 0x82008)
            d = intArrayOf(0x8000004, 0x20004, 0x0, 0x8020200, 0x20004, 0x200, 0x8000204, 0x20000, 0x204, 0x8020204, 0x20200, 0x8000000, 0x8000200, 0x8000004, 0x8020000, 0x20204, 0x20000, 0x8000204, 0x8020004, 0x0, 0x200, 0x4, 0x8020200, 0x8020004, 0x8020204, 0x8020000, 0x8000000, 0x204, 0x4, 0x20200, 0x20204, 0x8000200, 0x204, 0x8000000, 0x8000200, 0x20204, 0x8020200, 0x20004, 0x0, 0x8000200, 0x8000000, 0x200, 0x8020004, 0x20000, 0x20004, 0x8020204, 0x20200, 0x4, 0x8020204, 0x20200, 0x20000, 0x8000204, 0x8000004, 0x8020000, 0x20204, 0x0, 0x200, 0x8000004, 0x8000204, 0x8020200, 0x8020000, 0x204, 0x4, 0x8020004)
            e = intArrayOf(-0x7ffbff00, 0x1000100, -0x80000000, -0x7efbff00, 0x0, 0x1040000, -0x7effff00, -0x7ffc0000, 0x1040100, -0x7f000000, 0x1000000, -0x7fffff00, -0x7f000000, -0x7ffbff00, 0x40000, 0x1000000, -0x7efc0000, 0x40100, 0x100, -0x80000000, 0x40100, -0x7effff00, 0x1040000, 0x100, -0x7fffff00, -0x0, -0x7ffc0000, 0x1040100, 0x1000100, -0x7efc0000, -0x7efbff00, 0x40000, -0x7efc0000, -0x7fffff00, 0x40000, -0x7f000000, 0x40100, 0x1000100, -0x80000000, 0x1040000, -0x7effff00, -0x0, 0x100, -0x7ffc0000, -0x0, -0x7efc0000, 0x1040100, 0x100, 0x1000000, -0x7efbff00, -0x7ffbff00, 0x40000, -0x7efbff00, -0x80000000, 0x1000100, -0x7ffbff00, -0x7ffc0000, 0x40100, 0x1040000, -0x7effff00, -0x7fffff00, 0x1000000, -0x7f000000, 0x1040100)
            f = intArrayOf(0x4010801, 0x0, 0x10800, 0x4010000, 0x4000001, 0x801, 0x4000800, 0x10800, 0x800, 0x4010001, 0x1, 0x4000800, 0x10001, 0x4010800, 0x4010000, 0x1, 0x10000, 0x4000801, 0x4010001, 0x800, 0x10801, 0x4000000, 0x0, 0x10001, 0x4000801, 0x10801, 0x4010800, 0x4000001, 0x4000000, 0x10000, 0x801, 0x4010801, 0x10001, 0x4010800, 0x4000800, 0x10801, 0x4010801, 0x10001, 0x4000001, 0x0, 0x4000000, 0x801, 0x10000, 0x4010001, 0x800, 0x4000000, 0x10801, 0x4000801, 0x4010800, 0x800, 0x0, 0x4000001, 0x1, 0x4010801, 0x10800, 0x4010000, 0x4010001, 0x10000, 0x801, 0x4000800, 0x4000801, 0x1, 0x4010000, 0x10800)
            g = intArrayOf(0x400, 0x20, 0x100020, 0x40100000, 0x40100420, 0x40000400, 0x420, 0x0, 0x100000, 0x40100020, 0x40000020, 0x100400, 0x40000000, 0x100420, 0x100400, 0x40000020, 0x40100020, 0x400, 0x40000400, 0x40100420, 0x0, 0x100020, 0x40100000, 0x420, 0x40100400, 0x40000420, 0x100420, 0x40000000, 0x40000420, 0x40100400, 0x20, 0x100000, 0x40000420, 0x100400, 0x40100400, 0x40000020, 0x400, 0x20, 0x100000, 0x40100400, 0x40100020, 0x40000420, 0x420, 0x0, 0x20, 0x40100000, 0x40000000, 0x100020, 0x0, 0x40100020, 0x100020, 0x420, 0x40000020, 0x400, 0x40100420, 0x100000, 0x100420, 0x40000000, 0x40000400, 0x40100420, 0x40100000, 0x100420, 0x100400, 0x40000400)
            h = intArrayOf(0x800000, 0x1000, 0x40, 0x801042, 0x801002, 0x800040, 0x1042, 0x801000, 0x1000, 0x2, 0x800002, 0x1040, 0x800042, 0x801002, 0x801040, 0x0, 0x1040, 0x800000, 0x1002, 0x42, 0x800040, 0x1042, 0x0, 0x800002, 0x2, 0x800042, 0x801042, 0x1002, 0x801000, 0x40, 0x42, 0x801040, 0x801040, 0x800042, 0x1002, 0x801000, 0x1000, 0x2, 0x800002, 0x800040, 0x800000, 0x1040, 0x801042, 0x0, 0x1042, 0x800000, 0x40, 0x1002, 0x800042, 0x40, 0x0, 0x801042, 0x801002, 0x801040, 0x42, 0x1000, 0x1040, 0x801002, 0x800040, 0x42, 0x2, 0x1042, 0x801000, 0x800002)
            i = intArrayOf(0x10400000, 0x404010, 0x10, 0x10400010, 0x10004000, 0x400000, 0x10400010, 0x4010, 0x400010, 0x4000, 0x404000, 0x10000000, 0x10404010, 0x10000010, 0x10000000, 0x10404000, 0x0, 0x10004000, 0x404010, 0x10, 0x10000010, 0x10404010, 0x4000, 0x10400000, 0x10404000, 0x400010, 0x10004010, 0x404000, 0x4010, 0x0, 0x400000, 0x10004010, 0x404010, 0x10, 0x10000000, 0x4000, 0x10000010, 0x10004000, 0x404000, 0x10400010, 0x0, 0x404010, 0x4010, 0x10404000, 0x10004000, 0x400000, 0x10404010, 0x10000000, 0x10004010, 0x10400000, 0x400000, 0x10404010, 0x4000, 0x400010, 0x10400010, 0x4010, 0x400010, 0x0, 0x10404000, 0x10000010, 0x10400000, 0x10004010, 0x10, 0x404000)
            j = intArrayOf(0x208080, 0x8000, 0x20200000, 0x20208080, 0x200000, 0x20008080, 0x20008000, 0x20200000, 0x20008080, 0x208080, 0x208000, 0x20000080, 0x20200080, 0x200000, 0x0, 0x20008000, 0x8000, 0x20000000, 0x200080, 0x8080, 0x20208080, 0x208000, 0x20000080, 0x200080, 0x20000000, 0x80, 0x8080, 0x20208000, 0x80, 0x20200080, 0x20208000, 0x0, 0x0, 0x20208080, 0x200080, 0x20008000, 0x208080, 0x8000, 0x20000080, 0x200080, 0x20208000, 0x80, 0x8080, 0x20200000, 0x20008080, 0x20000000, 0x20200000, 0x208000, 0x20208080, 0x8080, 0x208000, 0x20200080, 0x200000, 0x20000080, 0x20008000, 0x0, 0x8000, 0x200000, 0x20200080, 0x208080, 0x20000000, 0x20208000, 0x80, 0x20008080)
        }

        private fun a(arg2: Int, arg3: Int): Int {
            return arg2 shl 32 - arg3 or arg2.ushr(arg3)
        }

        private fun a(arg5: ByteArray, arg6: Long) {
            var v0 = arg6.ushr(32).toInt()
            var v1 = a(arg6.toInt(), 31)
            var v2 = v0 xor v1 and 1431655765
            v1 = v1 xor v2
            v0 = a(v0 xor v2, 31)
            v2 = v1.ushr(8) xor v0 and 16711935
            v1 = v1 xor (v2 shl 8)
            v0 = v0 xor v2
            v2 = v0.ushr(2) xor v1 and 858993459
            v0 = v0 xor (v2 shl 2)
            v1 = v1 xor v2
            v2 = v1.ushr(16) xor v0 and 65535
            v1 = v1 xor (v2 shl 16)
            v0 = v0 xor v2
            v2 = v0.ushr(4) xor v1 and 252645135
            v0 = v0 xor (v2 shl 4)
            v1 = v1 xor v2
            arg5[0] = v1.toByte()
            arg5[1] = v1.ushr(8).toByte()
            arg5[2] = v1.ushr(16).toByte()
            arg5[3] = (v1 shr 24).toByte()
            arg5[4] = v0.toByte()
            arg5[5] = v0.ushr(8).toByte()
            arg5[6] = v0.ushr(16).toByte()
            arg5[7] = (v0 shr 24).toByte()
        }

        private fun c(arg6: ByteArray): Long {
            var v0 = arg6[0].toInt() and 255 or (arg6[1].toInt() and 255 shl 8) or (arg6[2].toInt() and 255 shl 16) or (arg6[3].toInt() and 255 shl 24)
            var v1 = arg6[4].toInt() and 255 or (arg6[5].toInt() and 255 shl 8) or (arg6[6].toInt() and 255 shl 16) or (arg6[7].toInt() and 255 shl 24)
            var v2 = v1.ushr(4) xor v0 and 252645135 shl 4 xor v1
            v0 = v0 xor (v1.ushr(4) xor v0 and 252645135)
            v1 = v0.ushr(16) xor v2 and 65535
            v0 = v0 xor (v0.ushr(16) xor v2 shl 16)
            v1 = v1 xor v2
            v2 = v1.ushr(2) xor v0 and 858993459
            v1 = v1 xor (v2 shl 2)
            v0 = v0 xor v2
            v2 = v0.ushr(8) xor v1 and 16711935
            v0 = v0 xor (v2 shl 8)
            v1 = a(v1 xor v2, 1)
            v2 = v0 xor v1 and 1431655765
            return (v1 xor v2).toLong() and 4294967295L or (a(v0 xor v2, 1).toLong() shl 32)
        }
    }
}
