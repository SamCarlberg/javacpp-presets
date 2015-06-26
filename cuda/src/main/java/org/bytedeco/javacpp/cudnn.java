// Targeted by JavaCPP version 1.0-SNAPSHOT

package org.bytedeco.javacpp;

import java.nio.*;
import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

import static org.bytedeco.javacpp.cuda.*;

public class cudnn extends org.bytedeco.javacpp.presets.cudnn {
    static { Loader.load(); }

// Parsed from <cudnn.h>

/*
 * Copyright 1993-2014 NVIDIA Corporation.  All rights reserved.
 *
 * NOTICE TO LICENSEE:
 *
 * This source code and/or documentation ("Licensed Deliverables") are
 * subject to NVIDIA intellectual property rights under U.S. and
 * international Copyright laws.
 *
 * These Licensed Deliverables contained herein is PROPRIETARY and
 * CONFIDENTIAL to NVIDIA and is being provided under the terms and
 * conditions of a form of NVIDIA software license agreement by and
 * between NVIDIA and Licensee ("License Agreement") or electronically
 * accepted by Licensee.  Notwithstanding any terms or conditions to
 * the contrary in the License Agreement, reproduction or disclosure
 * of the Licensed Deliverables to any third party without the express
 * written consent of NVIDIA is prohibited.
 *
 * NOTWITHSTANDING ANY TERMS OR CONDITIONS TO THE CONTRARY IN THE
 * LICENSE AGREEMENT, NVIDIA MAKES NO REPRESENTATION ABOUT THE
 * SUITABILITY OF THESE LICENSED DELIVERABLES FOR ANY PURPOSE.  IT IS
 * PROVIDED "AS IS" WITHOUT EXPRESS OR IMPLIED WARRANTY OF ANY KIND.
 * NVIDIA DISCLAIMS ALL WARRANTIES WITH REGARD TO THESE LICENSED
 * DELIVERABLES, INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY,
 * NONINFRINGEMENT, AND FITNESS FOR A PARTICULAR PURPOSE.
 * NOTWITHSTANDING ANY TERMS OR CONDITIONS TO THE CONTRARY IN THE
 * LICENSE AGREEMENT, IN NO EVENT SHALL NVIDIA BE LIABLE FOR ANY
 * SPECIAL, INDIRECT, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, OR ANY
 * DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS,
 * WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS
 * ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR PERFORMANCE
 * OF THESE LICENSED DELIVERABLES.
 *
 * U.S. Government End Users.  These Licensed Deliverables are a
 * "commercial item" as that term is defined at 48 C.F.R. 2.101 (OCT
 * 1995), consisting of "commercial computer software" and "commercial
 * computer software documentation" as such terms are used in 48
 * C.F.R. 12.212 (SEPT 1995) and is provided to the U.S. Government
 * only as a commercial end item.  Consistent with 48 C.F.R.12.212 and
 * 48 C.F.R. 227.7202-1 through 227.7202-4 (JUNE 1995), all
 * U.S. Government End Users acquire the Licensed Deliverables with
 * only those rights set forth herein.
 *
 * Any use of the Licensed Deliverables in individual and commercial
 * software must include, in the user documentation and internal
 * comments to the code, the above Disclaimer and U.S. Government End
 * Users Notice.
 */

 /*   cudnn : Neural Networks Library

 */

// #if !defined(CUDNN_H_)
// #define CUDNN_H_

public static final int CUDNN_VERSION = 2000;

// #include "driver_types.h"
// #include <cuda_runtime.h>

// #ifndef CUDNNWINAPI
// #ifdef _WIN32
// #define CUDNNWINAPI __stdcall
// #else
// #define CUDNNWINAPI
// #endif
// #endif

// #if defined (__cplusplus)
// #endif

@Opaque public static class cudnnContext extends Pointer {
    /** Empty constructor. */
    public cudnnContext() { }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public cudnnContext(Pointer p) { super(p); }
}

public static native @Cast("size_t") long cudnnGetVersion();

/*
 * CUDNN return codes
 */
/** enum cudnnStatus_t */
public static final int
    CUDNN_STATUS_SUCCESS          = 0,
    CUDNN_STATUS_NOT_INITIALIZED  = 1,
    CUDNN_STATUS_ALLOC_FAILED     = 2,
    CUDNN_STATUS_BAD_PARAM        = 3,
    CUDNN_STATUS_INTERNAL_ERROR   = 4,
    CUDNN_STATUS_INVALID_VALUE    = 5,
    CUDNN_STATUS_ARCH_MISMATCH    = 6,
    CUDNN_STATUS_MAPPING_ERROR    = 7,
    CUDNN_STATUS_EXECUTION_FAILED = 8,
    CUDNN_STATUS_NOT_SUPPORTED    = 9,
    CUDNN_STATUS_LICENSE_ERROR    = 10;

// human-readable error messages
public static native @Cast("const char*") BytePointer cudnnGetErrorString(@Cast("cudnnStatus_t") int status);

public static native @Cast("cudnnStatus_t") int cudnnCreate(@ByPtrPtr cudnnContext handle);
public static native @Cast("cudnnStatus_t") int cudnnDestroy(cudnnContext handle);
public static native @Cast("cudnnStatus_t") int cudnnSetStream(cudnnContext handle, CUstream_st streamId);
public static native @Cast("cudnnStatus_t") int cudnnGetStream(cudnnContext handle, @ByPtrPtr CUstream_st streamId);


/* Data structures to represent Image/Filter and the Neural Network Layer */
@Opaque public static class cudnnTensorStruct extends Pointer {
    /** Empty constructor. */
    public cudnnTensorStruct() { }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public cudnnTensorStruct(Pointer p) { super(p); }
}
@Opaque public static class cudnnConvolutionStruct extends Pointer {
    /** Empty constructor. */
    public cudnnConvolutionStruct() { }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public cudnnConvolutionStruct(Pointer p) { super(p); }
}
@Opaque public static class cudnnPoolingStruct extends Pointer {
    /** Empty constructor. */
    public cudnnPoolingStruct() { }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public cudnnPoolingStruct(Pointer p) { super(p); }
}
@Opaque public static class cudnnFilterStruct extends Pointer {
    /** Empty constructor. */
    public cudnnFilterStruct() { }
    /** Pointer cast constructor. Invokes {@link Pointer#Pointer(Pointer)}. */
    public cudnnFilterStruct(Pointer p) { super(p); }
}

/*
* CUDNN data type
*/
/** enum cudnnDataType_t */
public static final int
    CUDNN_DATA_FLOAT  = 0,
    CUDNN_DATA_DOUBLE = 1;

/* Create an instance of a generic Tensor descriptor */
public static native @Cast("cudnnStatus_t") int cudnnCreateTensorDescriptor( @ByPtrPtr cudnnTensorStruct tensorDesc );

/** enum cudnnTensorFormat_t */
public static final int
    CUDNN_TENSOR_NCHW = 0,   /* row major (wStride = 1, hStride = w) */
    CUDNN_TENSOR_NHWC = 1;    /* feature maps interleaved ( cStride = 1 )*/

public static native @Cast("cudnnStatus_t") int cudnnSetTensor4dDescriptor(   cudnnTensorStruct tensorDesc,
                                                        @Cast("cudnnTensorFormat_t") int format,
                                                        @Cast("cudnnDataType_t") int dataType,
                                                        int n,
                                                        int c,
                                                        int h,
                                                        int w
                                                    );


public static native @Cast("cudnnStatus_t") int cudnnSetTensor4dDescriptorEx( cudnnTensorStruct tensorDesc,
                                                        @Cast("cudnnDataType_t") int dataType,
                                                        int n,
                                                        int c,
                                                        int h,
                                                        int w,
                                                        int nStride,
                                                        int cStride,
                                                        int hStride,
                                                        int wStride
                                                      );

public static native @Cast("cudnnStatus_t") int cudnnGetTensor4dDescriptor(   cudnnTensorStruct tensorDesc,
                                                        @Cast("cudnnDataType_t*") IntPointer dataType,
                                                        IntPointer n,
                                                        IntPointer c,
                                                        IntPointer h,
                                                        IntPointer w,
                                                        IntPointer nStride,
                                                        IntPointer cStride,
                                                        IntPointer hStride,
                                                        IntPointer wStride
                                                    );
public static native @Cast("cudnnStatus_t") int cudnnGetTensor4dDescriptor(   cudnnTensorStruct tensorDesc,
                                                        @Cast("cudnnDataType_t*") IntBuffer dataType,
                                                        IntBuffer n,
                                                        IntBuffer c,
                                                        IntBuffer h,
                                                        IntBuffer w,
                                                        IntBuffer nStride,
                                                        IntBuffer cStride,
                                                        IntBuffer hStride,
                                                        IntBuffer wStride
                                                    );
public static native @Cast("cudnnStatus_t") int cudnnGetTensor4dDescriptor(   cudnnTensorStruct tensorDesc,
                                                        @Cast("cudnnDataType_t*") int[] dataType,
                                                        int[] n,
                                                        int[] c,
                                                        int[] h,
                                                        int[] w,
                                                        int[] nStride,
                                                        int[] cStride,
                                                        int[] hStride,
                                                        int[] wStride
                                                    );

public static native @Cast("cudnnStatus_t") int cudnnSetTensorNdDescriptor(  cudnnTensorStruct tensorDesc,
                                                       @Cast("cudnnDataType_t") int dataType,
                                                       int nbDims,
                                                       @Const IntPointer dimA,
                                                       @Const IntPointer strideA
                                                     );
public static native @Cast("cudnnStatus_t") int cudnnSetTensorNdDescriptor(  cudnnTensorStruct tensorDesc,
                                                       @Cast("cudnnDataType_t") int dataType,
                                                       int nbDims,
                                                       @Const IntBuffer dimA,
                                                       @Const IntBuffer strideA
                                                     );
public static native @Cast("cudnnStatus_t") int cudnnSetTensorNdDescriptor(  cudnnTensorStruct tensorDesc,
                                                       @Cast("cudnnDataType_t") int dataType,
                                                       int nbDims,
                                                       @Const int[] dimA,
                                                       @Const int[] strideA
                                                     );

public static native @Cast("cudnnStatus_t") int cudnnGetTensorNdDescriptor(  cudnnTensorStruct tensorDesc,
                                                       int nbDimsRequested,
                                                       @Cast("cudnnDataType_t*") IntPointer dataType,
                                                       IntPointer nbDims,
                                                       IntPointer dimA,
                                                       IntPointer strideA
                                                     );
public static native @Cast("cudnnStatus_t") int cudnnGetTensorNdDescriptor(  cudnnTensorStruct tensorDesc,
                                                       int nbDimsRequested,
                                                       @Cast("cudnnDataType_t*") IntBuffer dataType,
                                                       IntBuffer nbDims,
                                                       IntBuffer dimA,
                                                       IntBuffer strideA
                                                     );
public static native @Cast("cudnnStatus_t") int cudnnGetTensorNdDescriptor(  cudnnTensorStruct tensorDesc,
                                                       int nbDimsRequested,
                                                       @Cast("cudnnDataType_t*") int[] dataType,
                                                       int[] nbDims,
                                                       int[] dimA,
                                                       int[] strideA
                                                     );

/* PixelOffset( n, c, h, w ) = n *input_stride + c * feature_stride + h * h_stride + w * w_stride

   1)Example of all images in row major order one batch of features after the other (with an optional padding on row)
   input_stride :  c x h x h_stride
   feature_stride : h x h_stride
   h_stride  :  >= w  ( h_stride = w if no padding)
   w_stride  : 1


   2)Example of all images in row major with features maps interleaved
   input_stride :  c x h x h_stride
   feature_stride : 1
   h_stride  :  w x c
   w_stride  : c

   3)Example of all images in column major order one batch of features after the other (with optional padding on column)
   input_stride :  c x w x w_stride
   feature_stride : w x w_stride
   h_stride  :  1
   w_stride  :  >= h

*/

/* Destroy an instance of Tensor4d descriptor */
public static native @Cast("cudnnStatus_t") int cudnnDestroyTensorDescriptor( cudnnTensorStruct tensorDesc );


/* Tensor layout conversion helper (dest = alpha * src + beta * dest) */
public static native @Cast("cudnnStatus_t") int cudnnTransformTensor(   cudnnContext handle,
                                                  @Const Pointer alpha,
                                                  cudnnTensorStruct srcDesc,
                                                  @Const Pointer srcData,
                                                  @Const Pointer beta,
                                                  cudnnTensorStruct destDesc,
                                                  Pointer destData
                                                );

/** enum cudnnAddMode_t */
public static final int
   CUDNN_ADD_IMAGE   = 0,       /* add one image to every feature maps of each input */
   CUDNN_ADD_SAME_HW = 0,

   CUDNN_ADD_FEATURE_MAP = 1,   /* add a set of feature maps to a batch of inputs : tensorBias has n=1 , same nb feature than Src/dest */
   CUDNN_ADD_SAME_CHW    = 1,

   CUDNN_ADD_SAME_C      = 2,   /* add a tensor of size 1,c,1,1 to every corresponding point of n,c,h,w input */

   CUDNN_ADD_FULL_TENSOR = 3;    /* add 2 tensors with same n,c,h,w */

/* Tensor Bias addition : srcDest = alpha * bias + beta * srcDestDesc  */
public static native @Cast("cudnnStatus_t") int cudnnAddTensor(   cudnnContext handle,
                                            @Cast("cudnnAddMode_t") int mode,
                                            @Const Pointer alpha,
                                            cudnnTensorStruct biasDesc,
                                            @Const Pointer biasData,
                                            @Const Pointer beta,
                                            cudnnTensorStruct srcDestDesc,
                                            Pointer srcDestData
                                          );

/* Set all data points of a tensor to a given value : srcDest = value */
public static native @Cast("cudnnStatus_t") int cudnnSetTensor( cudnnContext handle,
                                          cudnnTensorStruct srcDestDesc,
                                          Pointer srcDestData,
                                          @Const Pointer value
                                         );

/* Set all data points of a tensor to a given value : srcDest = alpha * srcDest */
public static native @Cast("cudnnStatus_t") int cudnnScaleTensor(   cudnnContext handle,
                                              cudnnTensorStruct srcDestDesc,
                                              Pointer srcDestData,
                                              @Const Pointer alpha
                                          );

/*
 *  convolution mode
 */
/** enum cudnnConvolutionMode_t */
public static final int
    CUDNN_CONVOLUTION       = 0,
    CUDNN_CROSS_CORRELATION = 1;


/* Create an instance of FilterStruct */
public static native @Cast("cudnnStatus_t") int cudnnCreateFilterDescriptor( @ByPtrPtr cudnnFilterStruct filterDesc );

public static native @Cast("cudnnStatus_t") int cudnnSetFilter4dDescriptor(  cudnnFilterStruct filterDesc,
                                                       @Cast("cudnnDataType_t") int dataType,
                                                       int k,
                                                       int c,
                                                       int h,
                                                       int w
                                                  );

public static native @Cast("cudnnStatus_t") int cudnnGetFilter4dDescriptor(  cudnnFilterStruct filterDesc,
                                                       @Cast("cudnnDataType_t*") IntPointer dataType,
                                                       IntPointer k,
                                                       IntPointer c,
                                                       IntPointer h,
                                                       IntPointer w
                                                  );
public static native @Cast("cudnnStatus_t") int cudnnGetFilter4dDescriptor(  cudnnFilterStruct filterDesc,
                                                       @Cast("cudnnDataType_t*") IntBuffer dataType,
                                                       IntBuffer k,
                                                       IntBuffer c,
                                                       IntBuffer h,
                                                       IntBuffer w
                                                  );
public static native @Cast("cudnnStatus_t") int cudnnGetFilter4dDescriptor(  cudnnFilterStruct filterDesc,
                                                       @Cast("cudnnDataType_t*") int[] dataType,
                                                       int[] k,
                                                       int[] c,
                                                       int[] h,
                                                       int[] w
                                                  );

public static native @Cast("cudnnStatus_t") int cudnnSetFilterNdDescriptor(  cudnnFilterStruct filterDesc,
                                                       @Cast("cudnnDataType_t") int dataType,
                                                       int nbDims,
                                                       @Const IntPointer filterDimA
                                                     );
public static native @Cast("cudnnStatus_t") int cudnnSetFilterNdDescriptor(  cudnnFilterStruct filterDesc,
                                                       @Cast("cudnnDataType_t") int dataType,
                                                       int nbDims,
                                                       @Const IntBuffer filterDimA
                                                     );
public static native @Cast("cudnnStatus_t") int cudnnSetFilterNdDescriptor(  cudnnFilterStruct filterDesc,
                                                       @Cast("cudnnDataType_t") int dataType,
                                                       int nbDims,
                                                       @Const int[] filterDimA
                                                     );

public static native @Cast("cudnnStatus_t") int cudnnGetFilterNdDescriptor(  cudnnFilterStruct filterDesc,
                                                       int nbDimsRequested,
                                                       @Cast("cudnnDataType_t*") IntPointer dataType,
                                                       IntPointer nbDims,
                                                       IntPointer filterDimA
                                                    );
public static native @Cast("cudnnStatus_t") int cudnnGetFilterNdDescriptor(  cudnnFilterStruct filterDesc,
                                                       int nbDimsRequested,
                                                       @Cast("cudnnDataType_t*") IntBuffer dataType,
                                                       IntBuffer nbDims,
                                                       IntBuffer filterDimA
                                                    );
public static native @Cast("cudnnStatus_t") int cudnnGetFilterNdDescriptor(  cudnnFilterStruct filterDesc,
                                                       int nbDimsRequested,
                                                       @Cast("cudnnDataType_t*") int[] dataType,
                                                       int[] nbDims,
                                                       int[] filterDimA
                                                    );

public static native @Cast("cudnnStatus_t") int cudnnDestroyFilterDescriptor( cudnnFilterStruct filterDesc );

/* Create an instance of convolution descriptor */
public static native @Cast("cudnnStatus_t") int cudnnCreateConvolutionDescriptor( @ByPtrPtr cudnnConvolutionStruct convDesc );

public static native @Cast("cudnnStatus_t") int cudnnSetConvolution2dDescriptor(  cudnnConvolutionStruct convDesc,
                                                            int pad_h,
                                                            int pad_w,
                                                            int u,
                                                            int v,
                                                            int upscalex,
                                                            int upscaley,
                                                            @Cast("cudnnConvolutionMode_t") int mode
                                                         );


public static native @Cast("cudnnStatus_t") int cudnnGetConvolution2dDescriptor(   cudnnConvolutionStruct convDesc,
                                                             IntPointer pad_h,
                                                             IntPointer pad_w,
                                                             IntPointer u,
                                                             IntPointer v,
                                                             IntPointer upscalex,
                                                             IntPointer upscaley,
                                                             @Cast("cudnnConvolutionMode_t*") IntPointer mode
                                                          );
public static native @Cast("cudnnStatus_t") int cudnnGetConvolution2dDescriptor(   cudnnConvolutionStruct convDesc,
                                                             IntBuffer pad_h,
                                                             IntBuffer pad_w,
                                                             IntBuffer u,
                                                             IntBuffer v,
                                                             IntBuffer upscalex,
                                                             IntBuffer upscaley,
                                                             @Cast("cudnnConvolutionMode_t*") IntBuffer mode
                                                          );
public static native @Cast("cudnnStatus_t") int cudnnGetConvolution2dDescriptor(   cudnnConvolutionStruct convDesc,
                                                             int[] pad_h,
                                                             int[] pad_w,
                                                             int[] u,
                                                             int[] v,
                                                             int[] upscalex,
                                                             int[] upscaley,
                                                             @Cast("cudnnConvolutionMode_t*") int[] mode
                                                          );

/* Helper function to return the dimensions of the output tensor given a convolution descriptor */
public static native @Cast("cudnnStatus_t") int cudnnGetConvolution2dForwardOutputDim( cudnnConvolutionStruct convDesc,
                                                                 cudnnTensorStruct inputTensorDesc,
                                                                 cudnnFilterStruct filterDesc,
                                                                 IntPointer n,
                                                                 IntPointer c,
                                                                 IntPointer h,
                                                                 IntPointer w
                                                                );
public static native @Cast("cudnnStatus_t") int cudnnGetConvolution2dForwardOutputDim( cudnnConvolutionStruct convDesc,
                                                                 cudnnTensorStruct inputTensorDesc,
                                                                 cudnnFilterStruct filterDesc,
                                                                 IntBuffer n,
                                                                 IntBuffer c,
                                                                 IntBuffer h,
                                                                 IntBuffer w
                                                                );
public static native @Cast("cudnnStatus_t") int cudnnGetConvolution2dForwardOutputDim( cudnnConvolutionStruct convDesc,
                                                                 cudnnTensorStruct inputTensorDesc,
                                                                 cudnnFilterStruct filterDesc,
                                                                 int[] n,
                                                                 int[] c,
                                                                 int[] h,
                                                                 int[] w
                                                                );
                                                                                                                                

public static native @Cast("cudnnStatus_t") int cudnnSetConvolutionNdDescriptor( cudnnConvolutionStruct convDesc,
                                                           int arrayLength,  
                                                           @Const IntPointer padA,                                          
                                                           @Const IntPointer filterStrideA,         
                                                           @Const IntPointer upscaleA,              
                                                           @Cast("cudnnConvolutionMode_t") int mode
                                                         );
public static native @Cast("cudnnStatus_t") int cudnnSetConvolutionNdDescriptor( cudnnConvolutionStruct convDesc,
                                                           int arrayLength,  
                                                           @Const IntBuffer padA,                                          
                                                           @Const IntBuffer filterStrideA,         
                                                           @Const IntBuffer upscaleA,              
                                                           @Cast("cudnnConvolutionMode_t") int mode
                                                         );
public static native @Cast("cudnnStatus_t") int cudnnSetConvolutionNdDescriptor( cudnnConvolutionStruct convDesc,
                                                           int arrayLength,  
                                                           @Const int[] padA,                                          
                                                           @Const int[] filterStrideA,         
                                                           @Const int[] upscaleA,              
                                                           @Cast("cudnnConvolutionMode_t") int mode
                                                         );
                                                         
public static native @Cast("cudnnStatus_t") int cudnnGetConvolutionNdDescriptor( cudnnConvolutionStruct convDesc,
                                                           int arrayLengthRequested,
                                                           IntPointer arrayLength,
                                                           IntPointer padA,                                        
                                                           IntPointer strideA,
                                                           IntPointer upscaleA,
                                                           @Cast("cudnnConvolutionMode_t*") IntPointer mode
                                                         );
public static native @Cast("cudnnStatus_t") int cudnnGetConvolutionNdDescriptor( cudnnConvolutionStruct convDesc,
                                                           int arrayLengthRequested,
                                                           IntBuffer arrayLength,
                                                           IntBuffer padA,                                        
                                                           IntBuffer strideA,
                                                           IntBuffer upscaleA,
                                                           @Cast("cudnnConvolutionMode_t*") IntBuffer mode
                                                         );
public static native @Cast("cudnnStatus_t") int cudnnGetConvolutionNdDescriptor( cudnnConvolutionStruct convDesc,
                                                           int arrayLengthRequested,
                                                           int[] arrayLength,
                                                           int[] padA,                                        
                                                           int[] strideA,
                                                           int[] upscaleA,
                                                           @Cast("cudnnConvolutionMode_t*") int[] mode
                                                         );


/* Helper function to return the dimensions of the output tensor given a convolution descriptor */
public static native @Cast("cudnnStatus_t") int cudnnGetConvolutionNdForwardOutputDim( cudnnConvolutionStruct convDesc,
                                                                 cudnnTensorStruct inputTensorDesc,
                                                                 cudnnFilterStruct filterDesc,
                                                                 int nbDims,
                                                                 IntPointer tensorOuputDimA
                                                                );
public static native @Cast("cudnnStatus_t") int cudnnGetConvolutionNdForwardOutputDim( cudnnConvolutionStruct convDesc,
                                                                 cudnnTensorStruct inputTensorDesc,
                                                                 cudnnFilterStruct filterDesc,
                                                                 int nbDims,
                                                                 IntBuffer tensorOuputDimA
                                                                );
public static native @Cast("cudnnStatus_t") int cudnnGetConvolutionNdForwardOutputDim( cudnnConvolutionStruct convDesc,
                                                                 cudnnTensorStruct inputTensorDesc,
                                                                 cudnnFilterStruct filterDesc,
                                                                 int nbDims,
                                                                 int[] tensorOuputDimA
                                                                );

/* Destroy an instance of convolution descriptor */
public static native @Cast("cudnnStatus_t") int cudnnDestroyConvolutionDescriptor( cudnnConvolutionStruct convDesc );


/* helper function to provide the convolution algo that fit best the requirement */
/** enum cudnnConvolutionFwdPreference_t */
public static final int
    CUDNN_CONVOLUTION_FWD_NO_WORKSPACE        = 0,
    CUDNN_CONVOLUTION_FWD_PREFER_FASTEST      = 1,
    CUDNN_CONVOLUTION_FWD_SPECIFY_WORKSPACE_LIMIT = 2;  
                                  
/** enum cudnnConvolutionFwdAlgo_t */
public static final int
    CUDNN_CONVOLUTION_FWD_ALGO_IMPLICIT_GEMM         = 0,
    CUDNN_CONVOLUTION_FWD_ALGO_IMPLICIT_PRECOMP_GEMM = 1,
    CUDNN_CONVOLUTION_FWD_ALGO_GEMM                  = 2,
    CUDNN_CONVOLUTION_FWD_ALGO_DIRECT                = 3;

                                                       
public static native @Cast("cudnnStatus_t") int cudnnGetConvolutionForwardAlgorithm( cudnnContext handle,
                                                               cudnnTensorStruct srcDesc,
                                                               cudnnFilterStruct filterDesc,
                                                               cudnnConvolutionStruct convDesc, 
                                                               cudnnTensorStruct destDesc,
                                                               @Cast("cudnnConvolutionFwdPreference_t") int preference, 
                                                               @Cast("size_t") long memoryLimitInbytes,
                                                               @Cast("cudnnConvolutionFwdAlgo_t*") IntPointer algo                                                  
                                                             );
public static native @Cast("cudnnStatus_t") int cudnnGetConvolutionForwardAlgorithm( cudnnContext handle,
                                                               cudnnTensorStruct srcDesc,
                                                               cudnnFilterStruct filterDesc,
                                                               cudnnConvolutionStruct convDesc, 
                                                               cudnnTensorStruct destDesc,
                                                               @Cast("cudnnConvolutionFwdPreference_t") int preference, 
                                                               @Cast("size_t") long memoryLimitInbytes,
                                                               @Cast("cudnnConvolutionFwdAlgo_t*") IntBuffer algo                                                  
                                                             );
public static native @Cast("cudnnStatus_t") int cudnnGetConvolutionForwardAlgorithm( cudnnContext handle,
                                                               cudnnTensorStruct srcDesc,
                                                               cudnnFilterStruct filterDesc,
                                                               cudnnConvolutionStruct convDesc, 
                                                               cudnnTensorStruct destDesc,
                                                               @Cast("cudnnConvolutionFwdPreference_t") int preference, 
                                                               @Cast("size_t") long memoryLimitInbytes,
                                                               @Cast("cudnnConvolutionFwdAlgo_t*") int[] algo                                                  
                                                             );        
                                                                                                           
/*
 *  convolution algorithm (which requires potentially some workspace)
 */

 /* Helper function to return the minimum size of the workspace to be passed to the convolution given an algo*/ 
public static native @Cast("cudnnStatus_t") int cudnnGetConvolutionForwardWorkspaceSize( cudnnContext handle, 
                                                                   cudnnTensorStruct srcDesc,
                                                                   cudnnFilterStruct filterDesc,
                                                                   cudnnConvolutionStruct convDesc,  
                                                                   cudnnTensorStruct destDesc,
                                                                   @Cast("cudnnConvolutionFwdAlgo_t") int algo,
                                                                   @Cast("size_t*") SizeTPointer sizeInBytes
                                                                );        


/* Convolution functions: All of the form "output = alpha * Op(inputs) + beta * output" */

/* Function to perform the forward multiconvolution */
public static native @Cast("cudnnStatus_t") int cudnnConvolutionForward(        cudnnContext handle,
                                                          @Const Pointer alpha,
                                                          cudnnTensorStruct srcDesc,
                                                          @Const Pointer srcData,
                                                          cudnnFilterStruct filterDesc,
                                                          @Const Pointer filterData,
                                                          cudnnConvolutionStruct convDesc,
                                                          @Cast("cudnnConvolutionFwdAlgo_t") int algo,
                                                          Pointer workSpace,
                                                          @Cast("size_t") long workSpaceSizeInBytes,            
                                                          @Const Pointer beta,
                                                          cudnnTensorStruct destDesc,
                                                          Pointer destData
                                                 );

/* Functions to perform the backward multiconvolution */
public static native @Cast("cudnnStatus_t") int cudnnConvolutionBackwardBias(   cudnnContext handle,
                                                          @Const Pointer alpha,
                                                          cudnnTensorStruct srcDesc,
                                                          @Const Pointer srcData,
                                                          @Const Pointer beta,
                                                          cudnnTensorStruct destDesc,
                                                          Pointer destData
                                                      );
                                                      

                                                       
public static native @Cast("cudnnStatus_t") int cudnnConvolutionBackwardFilter( cudnnContext handle,
                                                          @Const Pointer alpha,
                                                          cudnnTensorStruct srcDesc,
                                                          @Const Pointer srcData,
                                                          cudnnTensorStruct diffDesc,
                                                          @Const Pointer diffData,
                                                          cudnnConvolutionStruct convDesc,
                                                          @Const Pointer beta,
                                                          cudnnFilterStruct gradDesc,
                                                          Pointer gradData
                                                        );


public static native @Cast("cudnnStatus_t") int cudnnConvolutionBackwardData(  cudnnContext handle,
                                                         @Const Pointer alpha,
                                                         cudnnFilterStruct filterDesc,
                                                         @Const Pointer filterData,
                                                         cudnnTensorStruct diffDesc,
                                                         @Const Pointer diffData,
                                                         cudnnConvolutionStruct convDesc,
                                                         @Const Pointer beta,
                                                         cudnnTensorStruct gradDesc,
                                                         Pointer gradData
                                                       );
                                                       
public static native @Cast("cudnnStatus_t") int cudnnIm2Col(  cudnnContext handle,
                                        @Const Pointer alpha,
                                        cudnnTensorStruct srcDesc,
                                        @Const Pointer srcData,
                                        cudnnFilterStruct filterDesc,                                        
                                        cudnnConvolutionStruct convDesc,
                                        Pointer colBuffer
                                     );


/*
 *  softmax algorithm
 */
/** enum cudnnSoftmaxAlgorithm_t */
public static final int
    CUDNN_SOFTMAX_FAST     = 0,        /* straightforward implementation */
    CUDNN_SOFTMAX_ACCURATE = 1;         /* subtract max from every point to avoid overflow */

/** enum cudnnSoftmaxMode_t */
public static final int
    CUDNN_SOFTMAX_MODE_INSTANCE = 0,   /* compute the softmax over all C, H, W for each N */
    CUDNN_SOFTMAX_MODE_CHANNEL = 1;     /* compute the softmax over all C for each H, W, N */

/* Softmax functions: All of the form "output = alpha * Op(inputs) + beta * output" */

/* Function to perform forward softmax */
public static native @Cast("cudnnStatus_t") int cudnnSoftmaxForward(  cudnnContext handle,
                                                @Cast("cudnnSoftmaxAlgorithm_t") int algorithm,
                                                @Cast("cudnnSoftmaxMode_t") int mode,
                                                @Const Pointer alpha,
                                                cudnnTensorStruct srcDesc,
                                                @Const Pointer srcData,
                                                @Const Pointer beta,
                                                cudnnTensorStruct destDesc,
                                                Pointer destData
                                             );

/* Function to perform backward softmax */
public static native @Cast("cudnnStatus_t") int cudnnSoftmaxBackward( cudnnContext handle,
                                                @Cast("cudnnSoftmaxAlgorithm_t") int algorithm,
                                                @Cast("cudnnSoftmaxMode_t") int mode,
                                                @Const Pointer alpha,
                                                cudnnTensorStruct srcDesc,
                                                @Const Pointer srcData,
                                                cudnnTensorStruct srcDiffDesc,
                                                @Const Pointer srcDiffData,
                                                @Const Pointer beta,
                                                cudnnTensorStruct destDiffDesc,
                                                Pointer destDiffData
                                              );

/*
 *  pooling mode
 */
/** enum cudnnPoolingMode_t */
public static final int
    CUDNN_POOLING_MAX     = 0,
    CUDNN_POOLING_AVERAGE_COUNT_INCLUDE_PADDING = 1, // count for average includes padded values
    CUDNN_POOLING_AVERAGE_COUNT_EXCLUDE_PADDING = 2; // count for average does not include padded values

/* Create an instance of pooling descriptor */
public static native @Cast("cudnnStatus_t") int cudnnCreatePoolingDescriptor( @ByPtrPtr cudnnPoolingStruct poolingDesc);

public static native @Cast("cudnnStatus_t") int cudnnSetPooling2dDescriptor(  cudnnPoolingStruct poolingDesc,
                                                        @Cast("cudnnPoolingMode_t") int mode,
                                                        int windowHeight,
                                                        int windowWidth,
                                                        int verticalPadding,
                                                        int horizontalPadding,
                                                        int verticalStride,
                                                        int horizontalStride
                                                   );

public static native @Cast("cudnnStatus_t") int cudnnGetPooling2dDescriptor(  cudnnPoolingStruct poolingDesc,
                                                        @Cast("cudnnPoolingMode_t*") IntPointer mode,
                                                        IntPointer windowHeight,
                                                        IntPointer windowWidth,
                                                        IntPointer verticalPadding,
                                                        IntPointer horizontalPadding,
                                                        IntPointer verticalStride,
                                                        IntPointer horizontalStride
                                                   );
public static native @Cast("cudnnStatus_t") int cudnnGetPooling2dDescriptor(  cudnnPoolingStruct poolingDesc,
                                                        @Cast("cudnnPoolingMode_t*") IntBuffer mode,
                                                        IntBuffer windowHeight,
                                                        IntBuffer windowWidth,
                                                        IntBuffer verticalPadding,
                                                        IntBuffer horizontalPadding,
                                                        IntBuffer verticalStride,
                                                        IntBuffer horizontalStride
                                                   );
public static native @Cast("cudnnStatus_t") int cudnnGetPooling2dDescriptor(  cudnnPoolingStruct poolingDesc,
                                                        @Cast("cudnnPoolingMode_t*") int[] mode,
                                                        int[] windowHeight,
                                                        int[] windowWidth,
                                                        int[] verticalPadding,
                                                        int[] horizontalPadding,
                                                        int[] verticalStride,
                                                        int[] horizontalStride
                                                   );

public static native @Cast("cudnnStatus_t") int cudnnSetPoolingNdDescriptor(  cudnnPoolingStruct poolingDesc,
                                                        @Cast("const cudnnPoolingMode_t") int mode,
                                                        int nbDims,
                                                        @Const IntPointer windowDimA,
                                                        @Const IntPointer paddingA,
                                                        @Const IntPointer strideA
                                                   );
public static native @Cast("cudnnStatus_t") int cudnnSetPoolingNdDescriptor(  cudnnPoolingStruct poolingDesc,
                                                        @Cast("const cudnnPoolingMode_t") int mode,
                                                        int nbDims,
                                                        @Const IntBuffer windowDimA,
                                                        @Const IntBuffer paddingA,
                                                        @Const IntBuffer strideA
                                                   );
public static native @Cast("cudnnStatus_t") int cudnnSetPoolingNdDescriptor(  cudnnPoolingStruct poolingDesc,
                                                        @Cast("const cudnnPoolingMode_t") int mode,
                                                        int nbDims,
                                                        @Const int[] windowDimA,
                                                        @Const int[] paddingA,
                                                        @Const int[] strideA
                                                   );

public static native @Cast("cudnnStatus_t") int cudnnGetPoolingNdDescriptor(  cudnnPoolingStruct poolingDesc,
                                                        int nbDimsRequested,
                                                        @Cast("cudnnPoolingMode_t*") IntPointer mode,
                                                        IntPointer nbDims,
                                                        IntPointer windowDimA,
                                                        IntPointer paddingA,
                                                        IntPointer strideA
                                                     );
public static native @Cast("cudnnStatus_t") int cudnnGetPoolingNdDescriptor(  cudnnPoolingStruct poolingDesc,
                                                        int nbDimsRequested,
                                                        @Cast("cudnnPoolingMode_t*") IntBuffer mode,
                                                        IntBuffer nbDims,
                                                        IntBuffer windowDimA,
                                                        IntBuffer paddingA,
                                                        IntBuffer strideA
                                                     );
public static native @Cast("cudnnStatus_t") int cudnnGetPoolingNdDescriptor(  cudnnPoolingStruct poolingDesc,
                                                        int nbDimsRequested,
                                                        @Cast("cudnnPoolingMode_t*") int[] mode,
                                                        int[] nbDims,
                                                        int[] windowDimA,
                                                        int[] paddingA,
                                                        int[] strideA
                                                     );






/* Destroy an instance of pooling descriptor */
public static native @Cast("cudnnStatus_t") int cudnnDestroyPoolingDescriptor( cudnnPoolingStruct poolingDesc );

/* Pooling functions: All of the form "output = alpha * Op(inputs) + beta * output" */

/* Function to perform forward pooling */
public static native @Cast("cudnnStatus_t") int cudnnPoolingForward(  cudnnContext handle,
                                                cudnnPoolingStruct poolingDesc,
                                                @Const Pointer alpha,
                                                cudnnTensorStruct srcDesc,
                                                @Const Pointer srcData,
                                                @Const Pointer beta,
                                                cudnnTensorStruct destDesc,
                                                Pointer destData
                                             );

/* Function to perform backward pooling */
public static native @Cast("cudnnStatus_t") int cudnnPoolingBackward( cudnnContext handle,
                                                cudnnPoolingStruct poolingDesc,
                                                @Const Pointer alpha,
                                                cudnnTensorStruct srcDesc,
                                                @Const Pointer srcData,
                                                cudnnTensorStruct srcDiffDesc,
                                                @Const Pointer srcDiffData,
                                                cudnnTensorStruct destDesc,
                                                @Const Pointer destData,
                                                @Const Pointer beta,
                                                cudnnTensorStruct destDiffDesc,
                                                Pointer destDiffData
                                              );

/*
 * activation mode
 */
/** enum cudnnActivationMode_t */
public static final int
    CUDNN_ACTIVATION_SIGMOID = 0,
    CUDNN_ACTIVATION_RELU    = 1,
    CUDNN_ACTIVATION_TANH    = 2;

/* Activation functions: All of the form "output = alpha * Op(inputs) + beta * output" */

/* Function to perform forward activation  */
public static native @Cast("cudnnStatus_t") int cudnnActivationForward( cudnnContext handle,
                                                  @Cast("cudnnActivationMode_t") int mode,
                                                  @Const Pointer alpha,
                                                  cudnnTensorStruct srcDesc,
                                                  @Const Pointer srcData,
                                                  @Const Pointer beta,
                                                  cudnnTensorStruct destDesc,
                                                  Pointer destData
                                                );

/* Function to perform backward activation  */
public static native @Cast("cudnnStatus_t") int cudnnActivationBackward( cudnnContext handle,
                                                   @Cast("cudnnActivationMode_t") int mode,
                                                   @Const Pointer alpha,
                                                   cudnnTensorStruct srcDesc,
                                                   @Const Pointer srcData,
                                                   cudnnTensorStruct srcDiffDesc,
                                                   @Const Pointer srcDiffData,
                                                   cudnnTensorStruct destDesc,
                                                   @Const Pointer destData,
                                                   @Const Pointer beta,
                                                   cudnnTensorStruct destDiffDesc,
                                                   Pointer destDiffData
                                                 );
// #if defined (__cplusplus)
// #endif

// #endif /* CUDNN_H_ */


}
